package io.qkits.zk.core.client;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import io.qkits.zk.core.ZkTaskObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: patrick on 2019-09-19
 * @Description:
 */
@Slf4j
public class QZkClient implements Watcher, Closeable {

  ZooKeeper zk;
  String hostWithPort;
  volatile boolean connected = false;
  volatile boolean expired = false;

  public QZkClient(String hostWithPort) {
    this.hostWithPort = hostWithPort;
  }

  void startZk() throws IOException {
    Watcher watcher;
    this.zk = new ZooKeeper(this.hostWithPort,
                            15000,
                            this);
  }

  @Override
  public void close() throws IOException {

  }

  @Override
  public void process(WatchedEvent watchedEvent) {
    log.info("event is []", watchedEvent);
    if (watchedEvent.getType() == Event.EventType.None) {
      switch (watchedEvent.getState()) {
        case SyncConnected:
          this.connected = true;
          break;
        case Disconnected:
          this.connected = true;
          break;
        case Expired:
          this.expired = true;
          connected = false;
          log.info("existing due to session expired ");
        default:
          break;
      }
    }
  }

  public boolean isConnected() {
    return connected;
  }

  public boolean isExpired() {
    return expired;
  }

  void submitTask(String task, ZkTaskObject taskCtx) {
    taskCtx.setTask(task);
    zk.create("/task/task-", task.getBytes(),
              ZooDefs.Ids.OPEN_ACL_UNSAFE,
              CreateMode.PERSISTENT_SEQUENTIAL,
              createTaskCallBack,
              taskCtx);
  }

  protected ConcurrentHashMap<String, Object> ctxMap = new ConcurrentHashMap<String, Object>();
  AsyncCallback.StringCallback createTaskCallBack = new AsyncCallback.StringCallback() {
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
      switch (KeeperException.Code.get(rc)) {
        case CONNECTIONLOSS:
          submitTask(
              ((ZkTaskObject) ctx).getTask(),
              (ZkTaskObject) ctx);
          break;
        case OK:
          log.info("task {} is created", name);
          ((ZkTaskObject) ctx).setTaskName(name);
          watchStatus(name.replace("/tasks/", "/status/"), ctx);
          break;
        default:
          log.error(
              "something went wrong" + KeeperException.create(KeeperException.Code.get(rc), path));
      }
    }
  };

  void watchStatus(String path, Object ctx) {
    ctxMap.put(path, ctx);
    zk.exists(path,
              statusWatcher,
              existsCallback,
              ctx);
  }

  Watcher statusWatcher = new Watcher() {
    public void process(WatchedEvent e) {
      if (e.getType() == Event.EventType.NodeCreated) {
        assert e.getPath().contains("/status/task-");
        assert ctxMap.containsKey(e.getPath());

        zk.getData(e.getPath(),
                   false,
                   getDataCallback,
                   ctxMap.get(e.getPath()));
      }
    }
  };
  AsyncCallback.StatCallback existsCallback = new AsyncCallback.StatCallback() {
    public void processResult(int rc, String path, Object ctx, Stat stat) {
      switch (KeeperException.Code.get(rc)) {
        case CONNECTIONLOSS:
          watchStatus(path, ctx);

          break;
        case OK:
          if (stat != null) {
            zk.getData(path, false, getDataCallback, ctx);
            log.info("Status node is there: " + path);
          }

          break;
        case NONODE:
          break;
        default:
          log.error("Something went wrong when " +
                    "checking if the status node exists: " +
                    KeeperException.create(KeeperException.Code.get(rc), path));

          break;
      }
    }
  };

  AsyncCallback.DataCallback getDataCallback = new AsyncCallback.DataCallback() {
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
      switch (KeeperException.Code.get(rc)) {
        case CONNECTIONLOSS:
          /*
           * Try again.
           */
          zk.getData(path, false, getDataCallback, ctxMap.get(path));
          return;
        case OK:
          /*
           *  Print result
           */
          String taskResult = new String(data);
          log.info("Task " + path + ", " + taskResult);

          /*
           *  Setting the status of the task
           */
          assert (ctx != null);
          ((ZkTaskObject) ctx).setStatus(taskResult.contains("done"));

          /*
           *  Delete status znode
           */
          //zk.delete("/tasks/" + path.replace("/status/", ""), -1, taskDeleteCallback, null);
          zk.delete(path, -1, taskDeleteCallback, null);
          ctxMap.remove(path);
          break;
        case NONODE:
          log.warn("Status node is gone!");
          return;
        default:
          log.error("Something went wrong here, " +
                    KeeperException.create(KeeperException.Code.get(rc), path));
      }
    }
  };

  AsyncCallback.VoidCallback taskDeleteCallback = new AsyncCallback.VoidCallback() {
    public void processResult(int rc, String path, Object ctx) {
      switch (KeeperException.Code.get(rc)) {
        case CONNECTIONLOSS:
          zk.delete(path, -1, taskDeleteCallback, null);
          break;
        case OK:
          log.info("Successfully deleted " + path);
          break;
        default:
          log.error("Something went wrong here, " +
                    KeeperException.create(KeeperException.Code.get(rc), path));
      }
    }
  };
}
