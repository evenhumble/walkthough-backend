package io.qkits.corejava.corejava.concurrency.concepts.blockqueuesamples;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by patrick on 16/6/12.
 */
public class LinkedBlockQueueSamples {
    /**
     * ArrayBlockingQueue：一个由数组支持的有界阻塞队列，
     * 规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(先入先出)顺序排序的。
     *
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(6);
        FakeConsumer consumer = new FakeConsumer(queue);
        Producer producer= new Producer(queue);

        for (int i = 0; i < 5; i++) {
            new Thread(producer,"Producer"+(i+1)).start();
            new Thread(consumer,"Consumer"+(i+1)).start();
        }
//        new Thread(consumer,"Consumer").start();
    }



}
