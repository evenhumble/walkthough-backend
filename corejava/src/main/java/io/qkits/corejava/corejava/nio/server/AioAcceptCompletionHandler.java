package io.qkits.corejava.corejava.nio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author mazhiqiang
 */
public class AioAcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AioTimeServerHandler attachment) {
        attachment.getAsynchronousServerSocketChannel().accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new AioReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioTimeServerHandler attachment) {
        attachment.getLatch().countDown();
    }
}
