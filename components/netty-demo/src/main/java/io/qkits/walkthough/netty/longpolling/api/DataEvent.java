package io.qkits.walkthough.netty.longpolling.api;

import java.util.Map;

import io.qkits.walkthough.netty.common.DataEntry;
import lombok.Data;

@Data
public class DataEvent {
    private DataEventType type;
    private String service;
    private String cluster;
    private Map<String, DataEntry> data;
}
