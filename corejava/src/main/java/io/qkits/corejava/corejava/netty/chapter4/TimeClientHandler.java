package io.qkits.corejava.corejava.netty.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author mazhiqiang
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

//    private final ByteBuf firstMessage;

    private int counter;
    private byte[] req;

    public TimeClientHandler() {
//        byte[] req = "QUERY TIME ORDER".getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
        req = ("Query Time Order" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf message = null;
//        for (int i = 0; i < 100; i++) {
//            message = Unpooled.buffer();
//            message.writeBytes(req);
//            ctx.writeAndFlush(message);
//        }
//        ctx.writeAndFlush(firstMessage);

        ByteBuf buf = null;
        for (int i = 0; i < 100; i++) {
            buf = Unpooled.buffer(req.length);
            buf.writeBytes(req);
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] req = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);
//        String body = new String(req, "UTF-8");
//        System.out.println(String.format("Now is : %s, counter is : %s", body, ++counter));

        String body = (String) msg;
        System.out.println(String.format("Now is : %s, the counter is : %s", body, ++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
