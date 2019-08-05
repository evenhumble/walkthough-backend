package io.hedwig.dh.sprintinternal;

import java.util.HashMap;
import java.util.Map;

import io.hedwig.dh.sprintinternal.io.ResourceLoader;


public abstract class AbstractBeanDefinitionReader implements
        BeanDefinitionReader {

    protected Map<String, BeanDefinition> register;
    protected ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.register = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegister() {
        return register;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
