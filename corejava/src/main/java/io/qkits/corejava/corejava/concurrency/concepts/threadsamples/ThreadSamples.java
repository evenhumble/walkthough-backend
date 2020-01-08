package io.qkits.corejava.corejava.concurrency.concepts.threadsamples;

/**
 * Created by patrick on 16/5/29.
 */
public class ThreadSamples {

    public static void main(String[] args) {
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("Hello "+threadName);
            }
        };
        Runnable task2 = () -> {
            String threadName = Thread.currentThread().getName();
//            while(true){
//                System.out.println("Hello "+threadName);
//            }
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello "+threadName);

        };
        task1.run();
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
        System.out.println("DONE!!!");
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        System.out.println(thread1.isAlive());
        System.out.println(thread2.isAlive());
        System.out.println(thread1.isDaemon());
        System.out.println(thread2.isDaemon());
        thread2.stop();
    }
}
