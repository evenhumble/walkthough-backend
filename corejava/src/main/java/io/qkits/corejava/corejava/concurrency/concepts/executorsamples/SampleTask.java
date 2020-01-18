package io.qkits.corejava.corejava.concurrency.concepts.executorsamples;

/**
 * Created by patrick on 16/8/27.
 */
public class SampleTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(10000L);
            System.out.println("this is thread "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
