package io.qkits.corejava.corejava.concurrency.concepts.concurrentmapsamples;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by patrick on 16/6/1.
 */
public class ConcurrentSamples {

    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");
        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));
        String value = map.putIfAbsent("c3", "p1");
        System.out.println(value);    // p0
        String value1 = map.getOrDefault("hi", "there");
        System.out.println(value1);    // there
        map.replaceAll((key, val) -> "r2".equals(key) ? "d3" : val);
        System.out.println(map.get("r2"));    // d3

        map.compute("foo", (key, val) -> value + val);
        System.out.println(map.get("foo"));   // barbar
        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));   // boo was foo
        //-Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        System.out.println(ForkJoinPool.getCommonPoolParallelism());  // 3

    }
}
