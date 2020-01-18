package io.qkits.corejava.corejava.concurrency.concepts.futuresmaples;


import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by patrick on 16/5/29.
 */
public class CallableFutureTaskSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        SampleTask t = new SampleTask();
        FutureTask<Map<String,String>> result = new FutureTask<>(t);
        es.submit(result);
        es.shutdown(); //interrupt the child thread
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("主线程在执行任务");
        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");


    }



}
