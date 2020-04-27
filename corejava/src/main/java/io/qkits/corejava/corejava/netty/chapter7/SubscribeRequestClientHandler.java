package io.qkits.corejava.corejava.netty.chapter7;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author mazhiqiang
 */
public class SubscribeRequestClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(generateSubscribeRequest(i));
        }
        ctx.flush();
    }

    private SubscribeRequest generateSubscribeRequest(int i) {
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setSubRequestId(i);
        subscribeRequest.setPhoneNumber("185xxxxxxxx");
        subscribeRequest.setProductName("Netty in action");
        subscribeRequest.setUserName("clamaa");
        return subscribeRequest;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(String.format("Receive server response : [%s]", msg));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
