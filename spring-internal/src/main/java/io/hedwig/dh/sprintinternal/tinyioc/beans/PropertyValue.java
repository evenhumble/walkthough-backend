package io.hedwig.dh.sprintinternal.tinyioc.beans;

import lombok.Getter;

/**
 * 用于bean的属性注入
 */
@Getter
public class PropertyValue {

  private final String name;

  private final Object value;

  public PropertyValue(String name, Object value) {
    this.name = name;
    this.value = value;
  }

}
