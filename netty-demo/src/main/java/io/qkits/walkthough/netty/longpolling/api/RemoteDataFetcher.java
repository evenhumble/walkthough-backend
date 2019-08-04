package io.qkits.walkthough.netty.longpolling.api;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.qkits.walkthough.netty.common.NettyWalkthroughContext;
import io.qkits.walkthough.netty.longpolling.handler.HttpChannelInitializer;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.UnresolvedAddressException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RemoteDataFetcher implements DataFetcher {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final EventLoopGroup worker = new NioEventLoopGroup(2);
    private final DataFetcherContext dataContext;
    private final Bootstrap bootstrap;
    private final URI uri;
    private Channel ch;

    public RemoteDataFetcher(DataFetcherContext dataContext) {
        this.dataContext = dataContext;
        this.bootstrap = new Bootstrap().channel(NioSocketChannel.class).option(ChannelOption.ALLOW_HALF_CLOSURE, true)
                .option(ChannelOption.SO_KEEPALIVE, true).group(worker).handler(new HttpChannelInitializer(dataContext));
        this.uri = URI.create(NettyWalkthroughContext.getUrl());
    }

    @Override
    public void register(DataEventRegistry registry) {
        dataContext.register(registry);
        fetch();
    }

    private void fetch() {
        executor.execute(() -> {
            try {
                try {
                    doFetch();
                } catch (IOException | UnresolvedAddressException e) {
                    Thread.sleep(5000L);
                    fetch();
                }
            } catch (InterruptedException e) {
            }
        });
    }

    private void doFetch() throws InterruptedException, IOException {
        if (ch != null && ch.isOpen()) {
            ch.close().sync();
        } else {
            ch = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
            ch.writeAndFlush(dataContext.toRequest());
            ch.closeFuture().addListener((future) -> {
                RemoteDataFetcher.this.fetch();
            });
        }
    }

    @Override
    public void stop() {
        executor.shutdownNow();
        worker.shutdownGracefully();
    }
}
