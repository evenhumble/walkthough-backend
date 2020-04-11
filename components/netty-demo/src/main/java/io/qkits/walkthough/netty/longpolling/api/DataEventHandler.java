package io.qkits.walkthough.netty.longpolling.api;

@FunctionalInterface
public interface DataEventHandler {
    void handle(DataEvent event);
}
