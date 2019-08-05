package io.hedwig.modules.netty.basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 1. author: patrick
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress("127.0.0.1", 8099));
        while (true) {
            SocketChannel socketChannel = channel.accept();
            doSomething(socketChannel);

        }

    }

    public static void doSomething(SocketChannel socketChannel) {
        System.out.println(socketChannel);
    }
}
