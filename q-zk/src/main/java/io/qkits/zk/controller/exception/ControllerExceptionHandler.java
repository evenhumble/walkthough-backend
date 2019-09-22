package io.qkits.zk.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: patrick on 2019-09-19
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Object handlerWebException(HttpServletRequest request){
     return request;
  }
}
