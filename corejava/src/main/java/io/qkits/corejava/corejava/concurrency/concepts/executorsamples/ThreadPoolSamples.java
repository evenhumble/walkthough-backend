package io.qkits.corejava.corejava.concurrency.concepts.executorsamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by patrick on 16/8/27.
 *
 */
public class ThreadPoolSamples {

    private ExecutorService executorService;

    private ThreadPoolSamples initFixedService(){
        executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        return this;
    }

    /**
     * 大小不限制的cached thread pool
     * @return
     */
    private ThreadPoolSamples initCachedService(){

        executorService=Executors.newCachedThreadPool();
        return this;
    }

    private ThreadPoolSamples initScheduleExecutor(){
        executorService=Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()*4);
        return this;
    }

    private ThreadPoolSamples initSingleThread(){
        executorService=Executors.newSingleThreadExecutor();
        return this;
    }

    public ThreadPoolSamples call(Runnable ...tasks){ //runnable is return void, while callable return object

        for (Runnable task : tasks) {
            executorService.submit(task);
        }
        return this;
    }

    public void endIt(){
        this.executorService.shutdown();
    }

    public ThreadFactory getThreadFactory(){
        return r -> {
            System.out.println("simple thread factory");
            return new Thread(r);
        };
    }
    public static void main(String[] args) {
//        new ThreadPoolSamples().initFixedService().call(new SampleTask()).endIt();
//        new ThreadPoolSamples().initFixedService().call(new SampleTask(),new SampleTask(),new SampleTask(),new SampleTask()).endIt();
        new ThreadPoolSamples().initSingleThread().call(new SampleTask()).endIt();
        new ThreadPoolSamples().initSingleThread().call(new SampleTask(),new SampleTask(),new SampleTask(),new SampleTask()).endIt();
    }
}
