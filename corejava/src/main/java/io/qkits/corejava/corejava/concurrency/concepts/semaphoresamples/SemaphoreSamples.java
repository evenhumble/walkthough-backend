package io.qkits.corejava.corejava.concurrency.concepts.semaphoresamples;

import java.util.concurrent.Semaphore;

/**
 * Created by patrick on 16/6/8.
 */
public class SemaphoreSamples {
    /**
     * Semaphore(int count) count数量的许可证信号量
     * acquire(int num) 获取num个许可证
     * release(int num) 释放num个许可证
     * 两个柜台,三个客户
     * @param args
     */
    public static void main(String[] args) {
        Semaphore se = new Semaphore(2);
        Person p1 = new Person("A",se);
        p1.start();
        Person p2 = new Person("B",se);
        p2.start();
        Person p3 =new Person("C",se);
        p3.start();

    }

    public static class Person extends Thread{
        private final String name;
        private final Semaphore signal;

        public Person(String name, Semaphore signal) {
            this.name = name;
            this.signal = signal;
        }

        @Override
        public void run() {
            System.out.println(getName()+"is waiting.....");
            try {
                signal.acquire();
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName()+"is done!");
            signal.release();
        }
    }
}
