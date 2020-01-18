package io.qkits.corejava.corejava.concurrency.concepts.coundDownLatchSample;

import java.util.concurrent.CountDownLatch;

/**
 * Created by patrick on 16/6/8.
 */
public class CountDownLatchSamples {
    /**
     * CountDownLatch(int count) 必须发生count个数量才可以打开锁存器
     * await(): 等待锁存器
     * countDown(): 触发事件
     *
     * @param args
     */
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Racer(countDownLatch,"A").start();
        new Racer(countDownLatch,"B").start();
        new Racer(countDownLatch,"C").start();
        for (int i = 0; i < 3; i++) {
            try{
                Thread.sleep(1000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(3-i);
            countDownLatch.countDown();
            if(i==2){
                System.out.println("Start");
            }
        }


    }

    public static class Racer extends Thread {
        private final CountDownLatch countDownLatch;
        private String name;

        public Racer(CountDownLatch countDownLatch, String name) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                for (int i = 0; i < 3; i++) {
                    System.out.println(getName() + ":" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
