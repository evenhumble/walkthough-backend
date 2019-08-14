package io.hedwig.dh.sprintinternal;

import java.util.LinkedHashSet;
import java.util.Set;

public class PropertyValues {

  private final Set<PropertyValue> values;

  public PropertyValues() {
    this.values = new LinkedHashSet<PropertyValue>();
  }

  public Set<PropertyValue> getValues() {
    return values;
  }

  public void add(PropertyValue value) {
    if (!values.add(value)) {
      throw new IllegalStateException("duplication value ! value = "
                                      + value);
    }
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
  }
}
