package io.qkits.walkthough.netty.shortpolling.api;


import io.qkits.walkthough.netty.common.NettyWalkthroughContext;

public class GlobalOperatorFactory {
    public static GlobalOperator newInstance() {
        if (NettyWalkthroughContext.isLocalized()) {
            return new LocalGlobalOperator();
        } else {
            return new RemoteGlobalOperator();
        }
    }
}
