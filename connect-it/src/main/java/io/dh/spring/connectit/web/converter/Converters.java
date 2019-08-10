package io.dh.spring.connectit.web.converter;

import io.dh.spring.connectit.testutils.DHReflections;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class Converters {

    public static <T,S> T convert(S sourceInstance,Class<T> targetClass){
        T targetInstance = DHReflections.instanceOf(targetClass);
        BeanUtils.copyProperties(sourceInstance,targetInstance);
        return targetInstance;
    }

    public static <T,S> List<T> convert(List<S> sourceInstances, Class<T> targetClass){

        List<T> targetInstances = new ArrayList<>();
        for (S sourceInstance : sourceInstances) {
            T targetInstance = DHReflections.instanceOf(targetClass);
            BeanUtils.copyProperties(sourceInstance,targetInstance);
            targetInstances.add(targetInstance);
        }

        return targetInstances;
    }
}
