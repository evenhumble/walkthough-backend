package io.qkits.corejava.corejava.netty.chapter8;

import com.yonyou.clamaa.netty.protobuf.PersonProtos;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author mazhiqiang
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PersonProtos.Person person = (PersonProtos.Person) msg;
        System.out.println("Server accept client message: "+ person.toString());
    }
}
