package io.hedwig.dh.sprintinternal.demos.

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类扫描器
 *
 * @author BYSocket
 * @since 2016-01-07 19:36:00
 */
public interface ClassScanner {
    public final static String CLASS_SCANNER = "org.jeff.class_scanner";

    /**
     * 获取指定包名中的所有类
     * @param packageName 包名
     * @return
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名中的所有类
     * @param packageName 包名
     * @param annotationClass 注解类
     * @return
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);
}