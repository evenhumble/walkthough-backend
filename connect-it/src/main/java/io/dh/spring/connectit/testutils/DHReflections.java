package io.dh.spring.connectit.testutils;

import io.dh.spring.connectit.common.exceptions.DHReflectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class DHReflections {
    private final static Logger logger = LoggerFactory.getLogger(DHReflections.class);
    private DHReflections (){}

    public static void setFieldValue(Object o, Field field, Object value){
        field.setAccessible(true);
        try {
            field.set(o,value);
        } catch (IllegalAccessException | IllegalArgumentException e ) {
            logger.error(String.format("set field %s value error,error_result=",
                    field.getName()
            ),e);
        }
    }


    public static <T> T instanceOf(Class<T> tClass){
        try {
            return tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
           logger.error("create instance failed",e);
           throw new DHReflectionException(e);
        }
    }

    /**
     * todo: getting all the fields until base class
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Field[] fieldsOf(Class<T> tClass){
        return tClass.getDeclaredFields();
    }

    public static <T> Object getFieldValue(Field field, T instance) {
        try {
            field.setAccessible(true);
            return field.get(instance);
        } catch (IllegalAccessException e) {
            logger.error("get field value failed,error=",e);
            throw new DHReflectionException(e);
        }
    }
}
