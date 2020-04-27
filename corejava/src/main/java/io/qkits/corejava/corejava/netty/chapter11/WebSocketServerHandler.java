package io.qkits.corejava.corejava.netty.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @author mazhiqiang
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            handleHttpRequest(ctx, fullHttpRequest);
        } else if (msg instanceof WebSocketFrame) {
            WebSocketFrame webSocketFrame = (WebSocketFrame) msg;
            handleWebSocketFrame(ctx, webSocketFrame);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.flush();
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

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.getDecoderResult().isSuccess() ||
                (!"websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, request,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory("ws://localhost:8888/websocket", null, false);
        handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            CloseWebSocketFrame closeWebSocketFrame = (CloseWebSocketFrame) frame;
            handshaker.close(ctx.channel(), closeWebSocketFrame.retain());
            return;
        }
        if (frame instanceof PingWebSocketFrame) {
            PingWebSocketFrame pingWebSocketFrame = (PingWebSocketFrame) frame;
            ctx.channel().write(new PongWebSocketFrame(pingWebSocketFrame.content().retain()));
            return;
        }

        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }
        TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) frame;
        String request = textWebSocketFrame.text();
        ctx.channel()
                .write(new TextWebSocketFrame(
                        String.format("%s, Welcome Netty WebSocket Service, currentTime: %tF", request, new Date())));
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request,
                                         FullHttpResponse response) {
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
        }

        ChannelFuture future = ctx.channel().writeAndFlush(response);
        if (response.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
