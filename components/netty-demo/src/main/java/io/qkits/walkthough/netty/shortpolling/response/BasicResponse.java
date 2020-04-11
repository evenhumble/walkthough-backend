package io.qkits.walkthough.netty.shortpolling.response;

import lombok.Data;

@Data
public class BasicResponse<T> {
    private T data;
    private String message;
    private String status;
}
