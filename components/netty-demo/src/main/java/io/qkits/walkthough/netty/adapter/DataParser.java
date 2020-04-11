package io.qkits.walkthough.netty.adapter;

import java.io.IOException;

import io.qkits.walkthough.netty.common.DataEntry;


public interface DataParser<T> {
    T parse(DataEntry data) throws IOException;
}
