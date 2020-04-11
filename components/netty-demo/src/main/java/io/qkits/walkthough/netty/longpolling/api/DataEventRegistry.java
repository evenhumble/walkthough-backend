package io.qkits.walkthough.netty.longpolling.api;

import lombok.Data;
import lombok.NonNull;

@Data
public class DataEventRegistry {
    @NonNull
    private final DataEventHandler dataEventHandler;
    @NonNull
    private final String service;
    @NonNull
    private final String cluster;
}
