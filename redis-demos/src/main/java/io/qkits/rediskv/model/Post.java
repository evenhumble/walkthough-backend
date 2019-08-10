package io.qkits.rediskv.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Post {
    private String content;
    private String uid;
    private String time = String.valueOf(System.currentTimeMillis());
    private String replyPid;
    private String replyUid;
}
