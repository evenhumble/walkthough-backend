package io.dh.spring.connectit.common.entity;

import io.dh.spring.connectit.testutils.DHReflections;
import jodd.bean.BeanUtil;

public class TreeEntityUtil {

    public static <T> T dummyRootEntity(Class<T> clazz){
        T instance = DHReflections.instanceOf(clazz);
        BeanUtil.pojo.setProperty(instance,"id",0);
        return instance;
    }
}
