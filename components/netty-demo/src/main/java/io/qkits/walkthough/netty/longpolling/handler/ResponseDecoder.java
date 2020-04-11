package io.qkits.walkthough.netty.longpolling.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.qkits.walkthough.netty.longpolling.response.Response;

public class ResponseDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            ctx.fireChannelRead(new ObjectMapper().readValue((String) msg, Response.class));
        }
    }
}
