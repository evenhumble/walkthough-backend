package io.qkits.qboot.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * ID
   */
//  @TableId(type = IdType.ID_WORKER_STR)
  @ApiModelProperty(value = "ID")
  private java.lang.String id;
  /**
   * 创建人
   */
  @ApiModelProperty(value = "创建人")
//  @Excel(name = "创建人", width = 15)
  private java.lang.String createBy;
  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
//  @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date createTime;
  /**
   * 更新人
   */
  @ApiModelProperty(value = "更新人")
//  @Excel(name = "更新人", width = 15)
  private java.lang.String updateBy;
  /**
   * 更新时间
   */
  @ApiModelProperty(value = "更新时间")
//  @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;
}