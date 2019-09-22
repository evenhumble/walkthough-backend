package io.qkits.qboot.user.web.dto;

import lombok.Data;

@Data
public class UserDetailsDTO {

  /**
   * 用户uuid
   */
  private String uuid;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 性别 0男 1女
   */
  private String sex;

  /**
   * 年龄
   */
  private String age;

}