package io.hedwig.dh.sprintinternal.demos.

import org.spring.ioc.IocConfig;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author BYSocket
 * @since 2016-01-11 16:39:00
 */
public class ClassScannerHelper {

    private static final ClassScanner classScanner = ClassScannerFactory.getClassScanner();

    /**
     * 获取基础包名中的所有类
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassList(IocConfig.pageName);
    }

    /**
     * 获取基础包名中指定注解的相关类
     * @param annotationClass 注解类
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(IocConfig.pageName,annotationClass);
    }
}
