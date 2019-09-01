package io.qkits.rediskv.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String pass;
    private String authKey;
}
