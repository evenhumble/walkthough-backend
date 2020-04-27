package io.qkits.corejava.corejava.netty.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @author mazhiqiang
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
//                //.substring(0, req.length - System.getProperty("line.separator").length());
//        System.out.println(String.format("The time server receive order : %s, the counter is : %s",
//                body, ++counter));
//
//        String currentTime =
//                "Query Time Order".equalsIgnoreCase(body) ? String.format("Current Time: %tF", new Date()) :
//                        "Bad Order";
//        ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());
//        ctx.write(response);


        String body = (String) msg;
        System.out.println(String.format("The time server receive order : %s, the count is : %s",
                body, ++counter));
        String currentTime = "Query Time Order".equalsIgnoreCase(body) ?
                String.format("current time: %tF", new Date()) : "Bad Order";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
