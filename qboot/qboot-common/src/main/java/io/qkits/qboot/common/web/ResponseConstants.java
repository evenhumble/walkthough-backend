package io.qkits.qboot.common.web;

/**
 * @author: patrick on 2019-08-30
 * @Description:
 */
public class ResponseConstants {

  /**
   * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
   */
  public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
  /**
   * {@code 200 OK} (HTTP/1.0 - RFC 1945)
   */
  public static final Integer SC_OK_200 = 200;

  /**
   * 访问权限认证未通过 510
   */
  public static final Integer SC_NO_AUTHZ = 401;
}
