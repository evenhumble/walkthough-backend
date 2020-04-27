package io.qkits.corejava.corejava.netty.chapter10;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 * @author mazhiqiang
 */
public class HttpClientDownloader {

    public static void main(String[] args) throws UnknownHostException, URISyntaxException, InterruptedException {

//        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        String url =
                "http://59.108.49.50:8088/dicmanager/retrieve?filetype=Region-000000000000000000000008-top100-top100-top100-merge&fileID=419&puser=formal_monitor50_20140715_cn&pswd=MTIzNDU2";

//        url = "http://www.baidu.com/oapi/reqAd.jsp?pub=923875870&adspace=65826983&adcount=1&response=HTML&devip=22.56.22.66&user=900&format=IMG&position=top&height=&width=&device=Mozilla%2F5.0%20%28Linux%3B%20Android%204.2.1%3B%20en-us%3B%20Nexus%204%20Build%2FJOP40D%29%20AppleWebKit%2F535.19%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F18.0.1025.166%20Mobile%20Safari%2F535.19&beacon=TRUE&phpsnip=104";
        QueryStringEncoder encoder = new QueryStringEncoder(url);
        URI uri = new URI(encoder.toString());
        FullHttpRequest request =
                new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString());
//        bootstrap.group(group).channel(NioSocketChannel.class)
//            .handler(new HttpClientHandler());
//        Channel channel = bootstrap.bind("59.108.49.50", 8088).sync().channel();
//        channel.writeAndFlush(request);
//
//        channel.closeFuture().sync();

        NioSocketChannel socketChannel = new NioSocketChannel(group.next());
        socketChannel.pipeline().addFirst(new HttpClientHandler());
        boolean registered = socketChannel.isRegistered();

        socketChannel.unsafe().register(socketChannel.newProgressivePromise());
        registered = socketChannel.isRegistered();

//        socketChannel.connect(new InetSocketAddress("59.108.49.50", 8088));
        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
        socketChannel.pipeline().write(request);
        socketChannel.flush();

        Thread.sleep(Long.MAX_VALUE);


    }

}
