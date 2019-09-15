package io.hedwig.dh.sprintinternal.tinyioc.beans.factory;

/**
 * bean container or factory for getting pre-initialized bean
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
