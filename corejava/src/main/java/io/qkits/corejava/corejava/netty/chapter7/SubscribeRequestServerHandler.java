package io.qkits.corejava.corejava.netty.chapter7;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author mazhiqiang
 */
public class SubscribeRequestServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeRequest subscribeRequest = (SubscribeRequest) msg;
        ctx.writeAndFlush(response(subscribeRequest.getSubRequestId()));
    }

    private SubscribeResponse response(int subscribeRequestId) {
        SubscribeResponse response = new SubscribeResponse();
        response.setSubRequestId(subscribeRequestId);
        response.setResponseCode(0);
        response.setDescription("Netty book order succeed, 3 days later, sent to designated address");
        return response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
