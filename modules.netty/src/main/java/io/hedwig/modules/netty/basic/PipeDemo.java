package io.hedwig.modules.netty.basic;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import io.netty.buffer.ByteBuf;

/**
 * 1. author: patrick
 */
public class PipeDemo {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String input="this is input";
        System.out.println(System.currentTimeMillis());
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.put(input.getBytes());
        buf.flip();
        while(buf.hasRemaining()){
            System.out.println(buf);
            sinkChannel.write(buf);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        sourceChannel.read(buf);
    }
}
