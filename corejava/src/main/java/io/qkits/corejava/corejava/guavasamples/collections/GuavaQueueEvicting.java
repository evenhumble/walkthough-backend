package io.qkits.corejava.corejava.guavasamples.collections;

import com.google.common.collect.EvictingQueue;

import java.util.Queue;

import static io.qkits.corejava.corejava.guavasamples.SampleUtil.*;

/**
 * Created by patrick on 16/6/23.
 */
public class GuavaQueueEvicting {

    /**
     * evicting queue, FIFO, if reach the size limitation,
     * pop the first one, push the new added one
     * @param args
     */
    public static void main(String[] args) {
        printTitle("Test Evicting queue");
        testEvictingQueue();
    }

    public static void testEvictingQueue(){
        Queue<String> evictingQueue = EvictingQueue.create(10);
        String message = "This is EvictingQueue";
        try {
            for (int i = 1; i <= 15; i++) {
                evictingQueue.add(message + i);
                println("EvictingQueue size: " + evictingQueue.size());
            }
        } catch (Exception e) {
            println("Exception Occurred: " + e);
        }

        printTitle("Poll Queue Evicting");
        while(!evictingQueue.isEmpty()){
            println(evictingQueue.poll());
        }


    }
}
