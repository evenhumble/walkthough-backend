package io.qkits.qboot.common.web;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.qkits.qboot.common.exception.QBootException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: patrick on 2019-08-30
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class QBootExceptionHandler {

  @ExceptionHandler(QBootException.class)
  public QResponse<?> handleRRException(QBootException e) {
    log.error(e.getMessage(), e);
    return QResponse.error(e.getMessage());
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public QResponse<?> handlerNoFoundException(Exception e) {
    log.error(e.getMessage(), e);
    return QResponse.error(404, "路径不存在，请检查路径是否正确");
  }

//  @ExceptionHandler(DuplicateKeyException.class)
//  public QResponse<?> handleDuplicateKeyException(DuplicateKeyException e){
//    log.error(e.getMessage(), e);
//    return QResponse.error("数据库中已存在该记录");
//  }
//
//  @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
//  public QResponse<?> handleAuthorizationException(AuthorizationException e){
//    log.error(e.getMessage(), e);
//    return QResponse.noauth("没有权限，请联系管理员授权");
//  }

  @ExceptionHandler(Exception.class)
  public QResponse<?> handleException(Exception e) {
    log.error(e.getMessage(), e);
    return QResponse.error(e.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public QResponse<?> HttpRequestMethodNotSupportedException(Exception e) {
    log.error(e.getMessage(), e);
    return QResponse.error("没有权限，请联系管理员授权");
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public QResponse<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
    log.error(e.getMessage(), e);
    return QResponse.error("文件大小超出10MB限制, 请压缩或降低文件质量! ");
  }

}
