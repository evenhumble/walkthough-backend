package io.qkits.corejava.corejava.concurrency.concepts.executorsamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by patrick on 16/5/29.
 */
public class ExecutorSamples {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            int count = 0;
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000L);
                    count++;
                    System.out.println(count);
                }
            } catch (InterruptedException e) {
                System.out.println(e);
                System.out.println(count);
                throw new RuntimeException("Thread is interrupted unexpectedly");
            }
            System.out.println("Current Thread Done!!!");
        });
        System.out.println(executor.isTerminated());
        System.out.println(executor.isShutdown());
        executor.shutdown();
        System.out.println(executor.isShutdown()); //always true
        Thread.sleep(2000L);
        executor.shutdownNow(); // interrupt the sleep
        System.out.println(executor.isShutdown()); //always true
        System.out.println("DONE!!!");
    }
}
