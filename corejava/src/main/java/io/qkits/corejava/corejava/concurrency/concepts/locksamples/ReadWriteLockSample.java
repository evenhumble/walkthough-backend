package io.qkits.corejava.corejava.concurrency.concepts.locksamples;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by patrick on 16/5/30.
 */
public class ReadWriteLockSample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(1);
                map.put("foo", "bar1");
            } finally {
                lock.writeLock().unlock();
            }
        });
        /**
         * write lock is locked ,need to wait the 1000L
         */
        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(1);
                map.put("foo", "bar2");
            } finally {
                lock.writeLock().unlock();
            }
        });

        /**
         * read-locks can safely be acquired concurrently as
         * long as no write-lock is held by another thread.
         */
        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.shutdown();
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
