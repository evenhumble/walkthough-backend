package io.hedwig.modules.netty.basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 1. author: patrick SocketChannel: 1. connect() 2. read() 3. write()
 */
public class SocketChannelDemo {

    private static void blockingSocketDemo() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("http://www.baidu.com", 8080));
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buf);
        channel.close();
    }

    public static void noiSocketDemo() throws IOException {

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("http://www.baidu.com", 8080));
        ByteBuffer buf = ByteBuffer.allocate(48);
        while(!channel.finishConnect()){
            channel.read(buf);
            while(buf.hasRemaining()){
                channel.write(buf);
            }
        }
        channel.close();
    }
    public static void main(String[] args) throws IOException {
        blockingSocketDemo();
    }
}
