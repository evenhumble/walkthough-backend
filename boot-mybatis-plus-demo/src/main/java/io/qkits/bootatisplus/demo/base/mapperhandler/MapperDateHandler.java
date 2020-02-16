package io.qkits.bootatisplus.demo.base.mapperhandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

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
        this.setFieldValByName("createTime",
                new Timestamp(System.currentTimeMillis()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("before update ....");
        this.setFieldValByName("createTime",
                new Timestamp(System.currentTimeMillis()), metaObject);
    }
}
