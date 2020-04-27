package io.qkits.corejava.corejava.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author mazhiqiang
 */
public class AioTimeServerHandler implements Runnable {

    private int port;
    private CountDownLatch latch;
    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AioTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(this.port));
            System.out.println("The time server is start in port: "+ this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public AsynchronousServerSocketChannel getAsynchronousServerSocketChannel() {
        return asynchronousServerSocketChannel;
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try{
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AioAcceptCompletionHandler());
    }
}
