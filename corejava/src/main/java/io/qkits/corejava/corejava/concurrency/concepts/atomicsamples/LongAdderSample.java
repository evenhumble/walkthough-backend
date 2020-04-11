package io.qkits.corejava.corejava.concurrency.concepts.atomicsamples;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import static io.qkits.corejava.corejava.concurrency.concepts.ConcurrentUtils.stop;


/**
 * Created by patrick on 16/6/1.
 */
public class LongAdderSample {

    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(adder::increment));


        System.out.println(adder.sumThenReset());
        //The drawback of LongAdder is higher memory consumption because a set of variables is held in-memory.
        System.out.println(adder.longValue());// => 1000

        LongBinaryOperator op = (x, y) -> 2 * x + y;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);


        IntStream.range(0, 10)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        stop(executor);

        System.out.println(accumulator.getThenReset());
        // => 2539
    }
}
