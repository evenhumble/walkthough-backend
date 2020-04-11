package io.qkits.walkthough.netty.adapter;

import lombok.Data;
import lombok.NonNull;

@Data
public class DataIncidentRegistry<T> {
    @NonNull
    private final DataIncidentHandler<T> dataIncidentHandler;
    @NonNull
    private final String service;
    @NonNull
    private final String cluster;
}
