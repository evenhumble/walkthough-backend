package io.hedwig.dh.sprintinternal.tinyioc.beans;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Property Containers
 */
public class PropertyValues {

  private final Map<String, PropertyValue> propertyValuesMap = new ConcurrentHashMap<>();

  public void addPropertyValue(PropertyValue pv) {
    //duplication is
    this.propertyValuesMap.put(pv.getName(), pv);
  }

  public Collection<PropertyValue> getPropertyValues() {
    return this.propertyValuesMap.values();
  }

}
