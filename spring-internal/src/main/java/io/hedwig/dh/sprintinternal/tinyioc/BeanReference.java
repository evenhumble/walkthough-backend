package io.hedwig.dh.sprintinternal.tinyioc;

import lombok.Data;

/**
 * BeanReference for IoC Container
 */
@Data
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

}
