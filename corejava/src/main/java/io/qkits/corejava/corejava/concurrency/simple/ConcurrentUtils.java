package io.qkits.corejava.corejava.concurrency.simple;

import java.util.concurrent.ExecutorService;

/**
 * @author: patrick on 2019-07-07
 * @Description:
 */
public class ConcurrentUtils {

  public static void sleep(long i) {

    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void stop(ExecutorService executor) {
    executor.shutdown();
  }
}
