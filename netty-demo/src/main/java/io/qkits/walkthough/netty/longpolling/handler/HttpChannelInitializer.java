package io.qkits.walkthough.netty.longpolling.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import io.qkits.walkthough.netty.longpolling.api.DataFetcherContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpChannelInitializer extends ChannelInitializer<Channel> {
    private final DataFetcherContext dataContext;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new IdleStateHandler(5, 300, 0));
        ch.pipeline().addLast(new HttpClientCodec());
        ch.pipeline().addLast(new HttpContentFilter());
        ch.pipeline().addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE));
        ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(new ResponseDecoder());
        ch.pipeline().addLast(new ResponseHandler(dataContext));
        ch.pipeline().addLast(new RequestBuilder());
        ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(new RequestEncoder());
    }
}
