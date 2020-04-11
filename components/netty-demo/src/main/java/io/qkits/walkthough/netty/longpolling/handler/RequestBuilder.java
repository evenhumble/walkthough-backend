package io.qkits.walkthough.netty.longpolling.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.qkits.walkthough.netty.common.NettyWalkthroughContext;

public class RequestBuilder extends ChannelOutboundHandlerAdapter {

  private final static Log logger = LogFactory.get(RequestBuilder.class);

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
      throws Exception {
    if (msg instanceof ByteBuf) {
      DefaultFullHttpRequest
          request =
          new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                                     NettyWalkthroughContext.getUrl() + "/data/long_poll",
                                     (ByteBuf) msg);
      request.headers().add(HttpHeaders.Names.CONTENT_TYPE, "application/json")
          .add(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes())
          .add(HttpHeaders.Names.AUTHORIZATION, NettyWalkthroughContext
              .getToken()).add(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
      ctx.write(request, promise);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    logger.error("Throwable caught in channel!", cause);
    ctx.close();
  }
}
