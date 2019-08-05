package io.dh.spring.connectit.common.mvc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, String> exceptionHandler(HttpServletRequest request, Exception e) {
        // 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
        Map<String, String> map = new HashMap<String, String>();
        map.put("statusCode", "100003");
        return map;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Map<String, String> runtimeExceptionHandler(HttpServletRequest request, RuntimeException e) {
        // 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
        Map<String, String> map = new HashMap<String, String>();
        map.put("statusCode", "100004");
        map.put("error", e.getMessage());
        return map;
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public Map<String, String> sqlExceptionHandler(HttpServletRequest request, SQLException e) {
        // 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
        Map<String, String> map = new HashMap<String, String>();
        map.put("statusCode", "100005");
        return map;
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public Map<String, String> ioExceptionHandler(HttpServletRequest request, IOException e) {
        // 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
        Map<String, String> map = new HashMap<String, String>();
        map.put("statusCode", "100006");
        return map;
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Map<String, String> npeHandler(HttpServletRequest request, NullPointerException e) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("statusCode", "100007");
        map.put("msg",e.getMessage());
        return map;
    }
}
