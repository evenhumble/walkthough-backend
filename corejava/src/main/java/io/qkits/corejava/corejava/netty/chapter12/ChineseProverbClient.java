package io.qkits.corejava.corejava.netty.chapter12;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author mazhiqiang
 */
public class ChineseProverbClient {

    public void run(int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChineseProverbClientHandler());
            Channel channel = bootstrap.bind(0).sync().channel();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("Query ....", CharsetUtil.UTF_8),
                    new InetSocketAddress("127.0.0.1", port))).sync();
            if (!channel.closeFuture().await(15000)) {
                System.out.println("Query Time Out!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChineseProverbClient().run(8888);
    }
}
