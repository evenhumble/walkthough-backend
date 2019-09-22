package io.hedwig.dh.sprintinternal.tinyioc.beans.factory;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.hedwig.dh.sprintinternal.tinyioc.BeanReference;
import io.hedwig.dh.sprintinternal.tinyioc.aop.BeanFactoryAware;
import io.hedwig.dh.sprintinternal.tinyioc.beans.BeanDefinition;
import io.hedwig.dh.sprintinternal.tinyioc.beans.PropertyValue;

/**
 * Autowired Bean Factory
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

  protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
    if (bean instanceof BeanFactoryAware) {
      ((BeanFactoryAware) bean).setBeanFactory(this);
    }
    for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
      Object value = propertyValue.getValue();
      if (value instanceof BeanReference) {
        BeanReference beanReference = (BeanReference) value;
        value = getBean(beanReference.getName());
      }

      try {
        Method declaredMethod = bean.getClass().getDeclaredMethod(
            "set" + propertyValue.getName().substring(0, 1).toUpperCase()
            + propertyValue.getName().substring(1), value.getClass());
        declaredMethod.setAccessible(true);

        declaredMethod.invoke(bean, value);
      } catch (NoSuchMethodException e) {
        Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
        declaredField.setAccessible(true);
        declaredField.set(bean, value);
      }
    }
  }
}
