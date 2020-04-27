package io.qkits.corejava.corejava.netty.chapter8;

import com.yonyou.clamaa.netty.protobuf.PersonProtos;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author mazhiqiang
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(generatePerson(i));
        }
        ctx.flush();
    }

    private PersonProtos.Person generatePerson(int i) {
        PersonProtos.Person clamaa =
                PersonProtos.Person.newBuilder().setId(i).setName("clamaa").setEmail("clark2mazhiqiang@163.com")
                        .setPhone(PersonProtos.Person.PhoneNumber.newBuilder().setNumber("13811102102")).build();
        return clamaa;
    }
}
