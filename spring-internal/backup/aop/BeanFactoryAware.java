package io.hedwig.dh.sprintinternal.aop;


import io.hedwig.dh.sprintinternal.factory.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory factory) throws Exception;

}
