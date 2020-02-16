package io.qkits.corejava.corejava.concurrency.concepts.futuresmaples;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by patrick on 16/5/29.
 */
public class SampleTask implements Callable<Map<String,String>> {
    @Override
    public Map<String, String> call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        Map<String,String> result = new HashMap();
        result.put(String.valueOf(Thread.currentThread()),Thread.currentThread().getName());
        return result;
    }
}
