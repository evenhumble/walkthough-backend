package io.qkits.qboot.common.view;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictModel implements Serializable {

  private static final long serialVersionUID = 1L;

  public DictModel() {
  }

  public DictModel(String value, String text) {
    this.value = value;
    this.text = text;
  }

  private String value;
  private String text;

  public String getTitle() {
    return this.text;
  }

}
