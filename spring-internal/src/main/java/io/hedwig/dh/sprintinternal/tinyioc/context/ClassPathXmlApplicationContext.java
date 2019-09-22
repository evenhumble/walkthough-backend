package io.hedwig.dh.sprintinternal.tinyioc.context;


import java.util.Map;

import io.hedwig.dh.sprintinternal.tinyioc.beans.BeanDefinition;
import io.hedwig.dh.sprintinternal.tinyioc.beans.factory.AbstractBeanFactory;
import io.hedwig.dh.sprintinternal.tinyioc.beans.factory.AutowireCapableBeanFactory;
import io.hedwig.dh.sprintinternal.tinyioc.beans.io.ResourceLoader;
import io.hedwig.dh.sprintinternal.tinyioc.beans.xml.XmlBeanDefinitionReader;

/**
 *
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

  private String configLocation;

  public ClassPathXmlApplicationContext(String configLocation) throws Exception {
    this(configLocation, new AutowireCapableBeanFactory());
  }

  public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory)
      throws Exception {
    super(beanFactory);
    this.configLocation = configLocation;
    refresh();
  }

  @Override
  protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
    XmlBeanDefinitionReader
        xmlBeanDefinitionReader =
        new XmlBeanDefinitionReader(new ResourceLoader());
    xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
    for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader
        .getRegistry().entrySet()) {
      beanFactory
          .registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
    }
  }

}
