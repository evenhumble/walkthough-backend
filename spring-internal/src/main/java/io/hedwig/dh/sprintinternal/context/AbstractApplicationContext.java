package io.hedwig.dh.sprintinternal.context;

import java.util.List;

import io.hedwig.dh.sprintinternal.BeanPostProcessor;
import io.hedwig.dh.sprintinternal.factory.AbstractBeanFactory;


public abstract class AbstractApplicationContext implements ApplicationContext {

    protected final AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(final AbstractBeanFactory factory) {
        this.beanFactory = factory;
    }

    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessors(AbstractBeanFactory beanFactory) {
        List<BeanPostProcessor> beanPostProcessors = beanFactory
                .getBeanByType(BeanPostProcessor.class);
        for (BeanPostProcessor processor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor(processor);
        }
    }

    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory)
            throws Exception;

}
