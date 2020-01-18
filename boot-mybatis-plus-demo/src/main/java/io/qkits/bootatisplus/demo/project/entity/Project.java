package io.qkits.bootatisplus.demo.project.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: patrick on 2020/1/4
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true )
@Accessors(chain = true)
public class Project extends Model<Project> {
  private Long id;
  private String projectName;
  private String projectSummary;
  private Long parentId;
}
