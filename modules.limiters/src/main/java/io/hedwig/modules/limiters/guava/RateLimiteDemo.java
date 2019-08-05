package io.hedwig.modules.limiters.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. author: patrick
 */
public class RateLimiteDemo {

    private static RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static void main(String[] args) {
//        testNoRateLimiter();
//        testWithRateLimiter();
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    testGlobalRateLimiter();
                }
            });
        }

        System.out.println("IT is DONE");
    }

    public static void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public static void testGlobalRateLimiter() {
//        while (rateLimiter.tryAcquire(1, 100, TimeUnit.MILLISECONDS)) {
            rateLimiter.acquire();
            System.out.println("this is testing-1");
//            break;
//        }

    }


    public static void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
