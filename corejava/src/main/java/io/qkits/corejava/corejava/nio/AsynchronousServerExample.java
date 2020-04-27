package io.qkits.corejava.corejava.nio;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * User: mazhqa
 * Date: 14-4-28
 */
public class AsynchronousServerExample {

    /**
     * Use asynchronousChannelGroup to do this thing, this will bind a thread-pool to process I/O event;
     * multi-async-channel will share thread-pool resource in the same channel group
     *
     * @throws IOException
     */
    public void startAsyncSimpleServer() throws IOException {
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                serverSocketChannel.accept(null, this);
                //then you can use result as client channel...

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                //you should consider how to process the exception...
            }
        });
    }

}
