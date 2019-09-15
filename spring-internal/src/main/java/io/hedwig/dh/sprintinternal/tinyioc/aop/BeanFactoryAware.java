package io.hedwig.dh.sprintinternal.tinyioc.aop;


import io.hedwig.dh.sprintinternal.tinyioc.beans.factory.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
