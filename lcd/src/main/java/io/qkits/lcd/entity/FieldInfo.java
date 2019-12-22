package io.qkits.lcd.entity;

import lombok.Data;

/**
 * @author: patrick on 2019/12/22
 * @Description:
 */
@Data
public class FieldInfo {

  private String columnName;
  private String fieldName;
  private String fieldClass;
  private String fieldComment;

}
