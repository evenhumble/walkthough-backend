package io.qkits.rediskv.model;


import lombok.Data;

@Data
public class Range {
    private static final int SIZE = 9;
    public int begin = 0;
    public int end = SIZE;

}
