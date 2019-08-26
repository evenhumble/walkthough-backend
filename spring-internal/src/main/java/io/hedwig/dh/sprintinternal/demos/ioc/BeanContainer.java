package io.hedwig.dh.sprintinternal.demos.ioc;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.hedwig.dh.sprintinternal.demos.clazz.ClassScannerHelper;

/**
 * Bean容器
 *  负责存Bean对象
 * @author BYSocket
 * @since 2016-01-11 16:17:00
 */
public class BeanContainer {

    /**
     * Bean Map (Bean 类 => Bean 实例)
     */
    private static final Map<Class<?>,Object> beanMap = new ConcurrentHashMap<Class<?>,Object>();
    static {
        try {
            // 获取应用包所有路径下的类
            List<Class<?>> classList = ClassScannerHelper.getClassList();
            for (Class<?> cls : classList) {
                // 如果是Bean注解的类
                if (cls.isAnnotationPresent(Bean.class)) {
                    // 创建Bean的实例
                    Object instance  = cls.newInstance();
                    // 将 Bean实例 放入 Bean Map 中（键为 Bean 类，值为 Bean 实例）
                    beanMap.put(cls,instance);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化Bean容器失败！",e);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }
}
