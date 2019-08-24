package io.qkits.aops.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.util.StringUtils;

import io.qkits.aops.AopConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * 基于 Redisson的 redis操作,包含lock和queue
 */

//@Component("redisOperation")
@Slf4j
public class RedisOperation {


  // @Resource
  private RedissonClient redissonClient;

  //远程Service默认的工作并发
  private int remoteServiceWorkersAmount = 1000;

  private int queueCapacity = 1000;

  //是否接收队列,默认是true,如果是false就不再接受消息,用于tomcat重启之前,保证tomcat不再接受队列
  private boolean receiveQueue = true;


  /**
   * 根据Key 和超时时间加锁
   */
  public boolean lock(String key, long expire) {
    if (StringUtils.isEmpty(key)) {
      return false;
    }

    try {
      RLock rLock = redissonClient.getLock(AopConstants.projectKeyPrefix + key + "_lock");
      //不做任何等待,抢不到就返回false
      if (rLock.tryLock(-1, expire, TimeUnit.MILLISECONDS)) {
        return true;
      }
      return false;
    } catch (InterruptedException e) {
      log.error("locking error", e);
      return false;
    }


  }

  /**
   * 根据Key解锁
   */
  public void unlock(String key) {

    if (StringUtils.isEmpty(key)) {
      return;
    }
    try {
      RLock rLock = redissonClient.getLock(AopConstants.projectKeyPrefix + key + "_lock");
      if (rLock.isLocked()) {
        rLock.unlock();
      }
    } catch (Throwable e) {
      log.error("unlock error", e);
    }
  }


  public <T> BlockingQueue<T> getBlockingQueue(String queueName, Class<T> clazz) {
    RBlockingQueue<T>
        queue =
        redissonClient.getBlockingQueue(AopConstants.projectKeyPrefix + queueName);
    return queue;
  }

  public RAtomicLong getAtomicLong(String name) {
    RAtomicLong atomicLong = redissonClient.getAtomicLong(AopConstants.projectKeyPrefix + name);
    return atomicLong;

  }

  public RAtomicLong getAtomicLong(String name, Long initValue) {
    RAtomicLong atomicLong = redissonClient.getAtomicLong(AopConstants.projectKeyPrefix + name);
    atomicLong.set(initValue);
    return atomicLong;

  }

  /**
   * 注册到远程Service服务(基于redisson实现的RPC)
   */
  public <T> void registerRemoteService(Class<T> clazz, T t) {

    RRemoteService remoteService = getRedissonClient().getRemoteService();
    // 注册了1000个服务端工作者实例，可以同时执行1000个并发调用
    remoteService.register(clazz, t, remoteServiceWorkersAmount);

  }

  /**
   * 获取远程的Service(基于redisson实现的RPC)
   */

  public <T> T getRemoteService(Class<T> clazz) {
    RRemoteService remoteService = getRedissonClient().getRemoteService();
    return remoteService.get(clazz);

  }

  public boolean getReceiveQueue() {
    return receiveQueue;
  }

  public void setReceiveQueue(boolean receiveQueue) {
    this.receiveQueue = receiveQueue;
  }


  public RedissonClient getRedissonClient() {
    return redissonClient;
  }

  public void setRedissonClient(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
  }


}
