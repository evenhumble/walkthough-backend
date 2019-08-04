package io.qkits.walkthough.netty.longpolling.api;


import io.qkits.walkthough.netty.common.NettyWalkthroughContext;

public class DataFetcherFactory {
    public static DataFetcher newInstance(DataFetcherContext dataContext) {
        if (NettyWalkthroughContext.isLocalized()) {
            return new LocalDataFetcher();
        } else {
            return new RemoteDataFetcher(dataContext);
        }
    }
}
