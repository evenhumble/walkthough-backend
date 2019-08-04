package io.qkits.walkthough.netty.shortpolling.api;

import io.qkits.walkthough.netty.common.DataType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DataOperatorContext {
    private final DataType dataType;
    private final String service;

    @Override
    public String toString() {
        return String.format("%s/%s", dataType.name().toLowerCase(), service);
    }
}
