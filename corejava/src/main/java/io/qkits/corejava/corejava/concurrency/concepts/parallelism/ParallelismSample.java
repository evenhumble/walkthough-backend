package io.qkits.corejava.corejava.concurrency.concepts.parallelism;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by patrick on 16/6/4.
 */
public class ParallelismSample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");
        map.forEach(1, (key, value) ->
                System.out.printf("Key:%s,Value:%s,Thread:%s \n", key, value, Thread.currentThread().getName()));

        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equalsIgnoreCase(key)) {
                return value;
            }
            return null;
        });
        System.out.println(result);
        String reduceValue = map.reduce(1,
                (key, value) -> {
                    System.out.println("Transform:" + Thread.currentThread().getName());
                    return key + "=" + value;
                }, (s1, s2) -> {
                    System.out.println(Thread.currentThread().getName());
                    return s1 + "," + s2;
                });
        System.out.println(reduceValue);
    }
}
