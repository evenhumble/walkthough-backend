package io.qkits.walkthough.netty.modules.netty.future;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * 1. author: patrick
 * 2. Usage
 * - Channel
 * - Channel Future
 * - ChannelFutureListener
 */
public class BasicClientFuture {

    public static void main(String[] args) {
        //1. create channel
        //2. Channel Future
        //3. ChannelFutureListener
        ChannelFutureListener listener=new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    ByteBuf buffer = Unpooled.copiedBuffer(
                        "Hello", Charset.defaultCharset()
                    );
                    ChannelFuture cf = future.channel()
                        .writeAndFlush(buffer);
                }else{
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        };
    }
}
