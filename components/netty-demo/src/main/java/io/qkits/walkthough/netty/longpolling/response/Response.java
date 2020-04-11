package io.qkits.walkthough.netty.longpolling.response;

import java.util.Map;

import io.qkits.walkthough.netty.common.DataEntry;
import lombok.Data;

@Data
public class Response {
    private String message;
    private Map<String, Map<String, Map<String, Map<String, DataEntry>>>> body;
}
