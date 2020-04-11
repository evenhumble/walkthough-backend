package io.qkits.walkthough.netty.shortpolling.api;


import io.qkits.walkthough.netty.common.NettyWalkthroughContext;

public class DataOperatorFactory {
    public static DataOperator newInstance(DataOperatorContext dataContext) {
        if (NettyWalkthroughContext.isLocalized()) {
            return new LocalDataOperator();
        } else {
            return new RemoteDataOperator(dataContext);
        }
    }
}
