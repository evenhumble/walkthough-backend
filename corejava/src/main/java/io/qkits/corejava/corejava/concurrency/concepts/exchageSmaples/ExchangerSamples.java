package io.qkits.corejava.corejava.concurrency.concepts.exchageSmaples;

import java.util.concurrent.Exchanger;

/**
 * Created by patrick on 16/6/8.
 */
public class ExchangerSamples {
    /**
     * Exchange: 制定进行交换的数据类型
     * V exchange(V object): 等待线程到达,交换数据
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> ex = new Exchanger<>();
        new A(ex).start();
        Thread.sleep(1000L);
        new B(ex).start();

    }

    public static class A extends Thread {
        private final Exchanger<String> ex;

        public A(Exchanger<String> ex) {
            this.ex = ex;
        }


        @Override
        public void run() {
            String str = null;
            try {
                str = ex.exchange("Hello?");
                System.out.println(str);
                str = ex.exchange("A");
                System.out.println(str);
                str = ex.exchange("B");
                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class B extends Thread {
        private final Exchanger<String> ex;

        public B(Exchanger<String> ex) {
            this.ex = ex;
        }


        @Override
        public void run() {
            String str = null;
            try {
                str = ex.exchange("Hello?");
                System.out.println(str);
                str = ex.exchange("1");
                System.out.println(str);
                str = ex.exchange("2");
                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
