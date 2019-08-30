package io.qkits.qboot.common.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: patrick on 2019-08-30
 * @Description:
 */
@Data
@ApiModel(value = "HTTP接口返回对象", description = "HTTP接口返回对象")
public class QResponse<T> {

  /**
   * 成功标志
   */
  @ApiModelProperty(value = "成功标志")
  private boolean success = true;

  /**
   * 返回处理消息
   */
  @ApiModelProperty(value = "返回处理消息")
  private String message = "操作成功！";

  /**
   * 返回代码
   */
  @ApiModelProperty(value = "返回代码")
  private Integer code = 0;

  /**
   * 返回数据对象 data
   */
  @ApiModelProperty(value = "返回数据对象")
  private T QResponse;

  /**
   * 时间戳
   */
  @ApiModelProperty(value = "时间戳")
  private long timestamp = System.currentTimeMillis();

  public QResponse() {

  }

  public QResponse<T> error500(String message) {
    this.message = message;
    this.code = ResponseConstants.SC_INTERNAL_SERVER_ERROR_500;
    this.success = false;
    return this;
  }

  public QResponse<T> success(String message) {
    this.message = message;
    this.code = ResponseConstants.SC_OK_200;
    this.success = true;
    return this;
  }


  public static QResponse<Object> ok() {
    QResponse<Object> r = new QResponse<Object>();
    r.setSuccess(true);
    r.setCode(ResponseConstants.SC_OK_200);
    r.setMessage("成功");
    return r;
  }

  public static QResponse<Object> ok(String msg) {
    QResponse<Object> r = new QResponse<Object>();
    r.setSuccess(true);
    r.setCode(ResponseConstants.SC_OK_200);
    r.setMessage(msg);
    return r;
  }

  public static QResponse<Object> ok(Object data) {
    QResponse<Object> r = new QResponse<Object>();
    r.setSuccess(true);
    r.setCode(ResponseConstants.SC_OK_200);
    r.setQResponse(data);
    return r;
  }

  public static QResponse<Object> error(String msg) {
    return error(ResponseConstants.SC_INTERNAL_SERVER_ERROR_500, msg);
  }

  public static QResponse<Object> error(int code, String msg) {
    QResponse<Object> r = new QResponse<Object>();
    r.setCode(code);
    r.setMessage(msg);
    r.setSuccess(false);
    return r;
  }

  /**
   * 无权限访问返回结果
   */
  public static QResponse<Object> AUTH_FAILED(String msg) {
    return error(ResponseConstants.SC_NO_AUTHZ, msg);
  }
}
