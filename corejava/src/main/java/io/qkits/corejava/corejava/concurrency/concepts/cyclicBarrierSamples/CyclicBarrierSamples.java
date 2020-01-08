package io.qkits.corejava.corejava.concurrency.concepts.cyclicBarrierSamples;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by patrick on 16/6/8.
 */
public class CyclicBarrierSamples {
    /**
     * CyclicBarrier(int num): 等待线程的数量
     * CyclicBarrier(int num,Runnable Action)
     * await()
     * @param args
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("Game start");
            }
        });

        new Player("A",cyclicBarrier).start();
        new Player("B",cyclicBarrier).start();
        new Player("C",cyclicBarrier).start();
        new Player("D",cyclicBarrier).start();
    }

    public static class Player extends Thread{
        private final CyclicBarrier cyclicBarrier;

        public Player(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public Player(String name,CyclicBarrier cyclicBarrier){
            super(name);
            this.cyclicBarrier=cyclicBarrier;
        }


        @Override
        public void run() {
            System.out.println(getName()+"is waiting other players");
            try{
                cyclicBarrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
