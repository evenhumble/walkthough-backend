package io.qkits.walkthough.netty.longpolling.api;

public interface DataFetcher {
    void register(DataEventRegistry registry);

    void stop();
}
