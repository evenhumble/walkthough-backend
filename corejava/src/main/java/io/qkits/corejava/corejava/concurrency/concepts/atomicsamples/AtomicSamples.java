package io.qkits.corejava.corejava.concurrency.concepts.atomicsamples;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import io.allroundtester.walkthrough.corejava.concurrency.concepts.ConcurrentUtils;

/**
 * Created by patrick on 16/6/1.
 */
public class AtomicSamples {

    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));
        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.updateAndGet(operand -> operand +2);
                    executor.submit(task);
                });
        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, (n, m) -> n + m);
                    executor.submit(task);
                });
        ConcurrentUtils.stop(executor);

        System.out.println(atomicInt.get());    // => 1000
    }


}
