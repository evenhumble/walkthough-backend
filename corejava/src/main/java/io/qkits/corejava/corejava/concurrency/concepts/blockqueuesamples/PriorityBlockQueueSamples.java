package io.qkits.corejava.corejava.concurrency.concepts.blockqueuesamples;

import io.qkits.corejava.corejava.concurrency.concepts.ConcurrentUtils;

import java.util.Comparator;
import java.util.concurrent.*;



/**
 * Created by patrick on 16/6/12.
 */
public class PriorityBlockQueueSamples {
    /**
     * ArrayBlockingQueue：一个由数组支持的有界阻塞队列，
     * 规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(先入先出)顺序排序的。
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>(10, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        FakeConsumer consumer = new FakeConsumer(queue);
        Producer producer= new Producer(queue);

        for (int i = 0; i < 5; i++) {
            new Thread(producer,"Producer"+(i+1)).start();

        }
//        new Thread(consumer,"Consumer").start();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(consumer);
        service.submit(consumer);
        service.submit(consumer);
        service.submit(consumer);
        service.submit(consumer);
        ConcurrentUtils.stop(service);
    }



}
