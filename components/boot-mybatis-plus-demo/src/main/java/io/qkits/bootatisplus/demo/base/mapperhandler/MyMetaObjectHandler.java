//package io.qkits.bootatisplus.demo.base.mapperhandler;
//
//import java.sql.Timestamp;
//
//import org.apache.ibatis.reflection.MetaObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//
///**
// * 填充器
// *
// */
//@Component
//public class MyMetaObjectHandler implements MetaObjectHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);
//
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        LOGGER.info("start insert fill ....");
//        //避免使用metaObject.setValue()
//        this.setFieldValByName("createTime", new Timestamp(System.currentTimeMillis()), metaObject);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        LOGGER.info("nothing to fill ....");
//    }
//}
