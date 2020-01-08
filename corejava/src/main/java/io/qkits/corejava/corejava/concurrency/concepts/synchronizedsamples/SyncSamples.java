package io.qkits.corejava.corejava.concurrency.concepts.synchronizedsamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by patrick on 16/5/30.
 */
public class SyncSamples {
    int count = 0;

    /**
     * (i) read the current value,
     * (ii) increase this value by one and
     * (iii) write the new value to the variable, if at this point, other thread read it
     *  and add one to it, then it lost one write
     * so not atomic operation
     */
    void increment(){
        count+=1;
    }

    /**
     * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
     * All implicit monitors implement the reentrant characteristics
     * Reentrant means that locks are bound to the current thread.
     * A thread can safely acquire the same lock multiple times without running into deadlocks
     */
    synchronized void incrementS(){
        count+=1;
    }


    /**
     * instead of using implicit locking via the synchronized keyword the
     * Concurrency API supports various explicit locks specified by the Lock interface.
     * Locks support various methods for
     * finer grained lock control thus are more expressive than implicit monitors
     * The class ReentrantLock is a mutual exclusion lock
     * with the same basic behavior as the implicit monitors
     */
    ReentrantLock lock = new ReentrantLock(); //mutual exclusion lock, same thread can reentrant
    void incrementL() {
        System.out.println(lock.isLocked());
        System.out.println(lock.isHeldByCurrentThread());
        lock.lock();
        System.out.println(lock.isLocked());
        System.out.println(lock.isHeldByCurrentThread());
        System.out.println(lock.isFair());
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void countWOSync() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        IntStream.range(1,10000).forEach(
                item->executor.submit(() -> this.increment())
        );
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println(this.count);
    }

    public void countWL() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        IntStream.range(1,10000).forEach(
                item->executor.submit(() -> this.incrementL())
        );
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println(this.count);
    }

    public void countWSync() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        IntStream.range(1,10000).forEach(
                item->executor.submit(() -> this.incrementS())
        );
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println(this.count);
    }
    public static void main(String[] args) throws InterruptedException {
        final SyncSamples samples1 = new SyncSamples();
        final SyncSamples samples2 = new SyncSamples();
        final SyncSamples samples3 = new SyncSamples();
        samples1.countWOSync();
        samples2.countWSync();
        samples3.countWL();

    }
}
