package io.qkits.walkthough.netty.adapter;

import lombok.Data;

@Data
public class DataIncident<T> {
    private DataIncidentType type;
    private String service;
    private String cluster;
    private String key;
    private T value;
}
