package io.qkits.qboot.user.web.dto;

import lombok.Data;

/**
 * @author: patrick on 2019-09-14
 * @Description:
 */
@Data
public class LoginDTO {

  /**
   * 用户名称
   */
  private String username;

  /**
   * 用户token
   */
  private String token;
}
