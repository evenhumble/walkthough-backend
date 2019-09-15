package io.hedwig.dh.sprintinternal.tinyioc.beans;

/**
 * load BeanDefinition
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
