package io.hedwig.modules.netty.basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 1. author: patrick
 * UDP protocol
 */
public class DatagramChannelDemo {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel =  DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));
        //recieve data
        ByteBuffer buf = ByteBuffer.allocate(256);
        buf.clear();
        channel.receive(buf);

        //send data
        String newData = "This is Input String";
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        channel.send(buf,new InetSocketAddress(8099));
        //connect to given server
        channel.connect(new InetSocketAddress(9999));
        channel.read(buf);
        channel.write(buf);
    }
}
