package io.qkits.corejava.corejava.concurrency.concepts.blockqueuesamples;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
        BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            int endInt = new Random().nextInt(10);
            for (int i = 0; i <endInt ; i++) {
                sb.append("again");
            }
            String temp = "A Product, producer thread:" + Thread.currentThread().getName()+sb.toString();
            System.out.println("I have made a product:" + Thread.currentThread().getName());
            try {
                queue.put(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }