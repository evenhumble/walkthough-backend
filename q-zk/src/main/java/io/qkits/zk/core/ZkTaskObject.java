package io.qkits.zk.core;

import java.util.concurrent.CountDownLatch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: patrick on 2019-09-19
 * @Description:
 */
@Data
@Slf4j
public class ZkTaskObject {
  private String task;
  private String taskName;
  private boolean done = false;
  private boolean successful = false;
  private CountDownLatch latch = new CountDownLatch(1);

  public void waitUntilDone () {
    try{
      latch.await();
    } catch (InterruptedException e) {
      log.warn("InterruptedException while waiting for task to get done");
    }
  }
  public void setStatus (boolean status){
    successful = status;
    done = true;
    latch.countDown();
  }
}
