package io.hedwig.dh.sprintinternal.tinyioc.beans;

public interface BeanPostProcessor {

  Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

  Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;

}