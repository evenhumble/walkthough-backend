package io.qkits.walkthough.netty.longpolling.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.qkits.walkthough.netty.longpolling.api.DataFetcherContext;
import io.qkits.walkthough.netty.longpolling.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ResponseHandler extends ChannelInboundHandlerAdapter {

  private final DataFetcherContext dataContext;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof Response) {
      dataContext.handleMessage((Response) msg);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.error("Throwable caught in channel!", cause);
    ctx.close();
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      ctx.close();
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }
}
