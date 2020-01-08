package io.qkits.corejava.corejava.concurrency.concepts.blockqueuesamples;

import java.util.concurrent.BlockingQueue;

public class FakeConsumer implements Runnable {

        BlockingQueue<String> queue;

        public FakeConsumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                String temp = queue.take();
                System.out.println(Thread.currentThread().getName() + "taking the " + temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }