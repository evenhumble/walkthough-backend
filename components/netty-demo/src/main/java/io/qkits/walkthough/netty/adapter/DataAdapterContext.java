package io.qkits.walkthough.netty.adapter;

import io.qkits.walkthough.netty.common.DataType;
import lombok.Data;
import lombok.NonNull;

@Data
public class DataAdapterContext<T> {
    @NonNull
    private final DataType dataType;
    @NonNull
    private final DataParser<T> dataParser;
}
