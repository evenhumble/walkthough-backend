package io.qkits.lcd.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author: patrick on 2019/12/22
 * @Description:
 */
@Data
public class EntityClassInfo {
  private String tableName;
  private String className;
  private String classComment;
  private List<FieldInfo> fieldList = new ArrayList<>();
}
