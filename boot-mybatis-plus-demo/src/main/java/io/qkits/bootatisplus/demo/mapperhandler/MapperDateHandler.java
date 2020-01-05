package io.qkits.bootatisplus.demo.mapperhandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: patrick on 2020/1/5
 * @Description:
 */
@Slf4j
@Component
public class MapperDateHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("before insert ....");
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("before update ....");
  }
}
