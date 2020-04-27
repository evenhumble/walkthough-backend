package io.qkits.corejava.corejava.netty.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author mazhiqiang
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("Http Status: " + response.getStatus());
            System.out.println("Http Version: " + response.getProtocolVersion());
            System.out.println("-------------------------");

            if (!response.headers().isEmpty()) {
                for (String headerName : response.headers().names()) {
                    for (String value : response.headers().getAll(headerName)) {
                        System.out.println(String.format("Http Header: [%s=%s]", headerName, value));
                    }
                }
            }

            if (HttpHeaders.isTransferEncodingChunked(response)) {
                System.out.println("Chunked Content{");
            } else {
                System.out.println("Content{");
            }
        }

        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            System.out.println(httpContent.content().toString(CharsetUtil.UTF_8));
            System.out.flush();
            if (httpContent instanceof LastHttpContent) {
                System.out.println("} END OF CONTENT");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        messageReceived(ctx, (HttpObject) msg);
    }
}
