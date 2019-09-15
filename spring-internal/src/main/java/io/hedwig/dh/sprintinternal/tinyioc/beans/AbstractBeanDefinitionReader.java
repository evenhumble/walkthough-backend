package io.hedwig.dh.sprintinternal.tinyioc.beans;


import java.util.HashMap;
import java.util.Map;

import io.hedwig.dh.sprintinternal.tinyioc.beans.io.ResourceLoader;

/**
 * Read Bean Definition
 * 
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
