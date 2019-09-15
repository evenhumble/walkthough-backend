package io.qkits.qboot.user.web.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginRequest {

  /**
   * 登录用户名称
   */
  @NotNull(message = "用户名不允许为空")
  private String userName;

  /**
   * 登录用户密码
   */
  @NotNull(message = "密码不允许为空")
  private String password;

}