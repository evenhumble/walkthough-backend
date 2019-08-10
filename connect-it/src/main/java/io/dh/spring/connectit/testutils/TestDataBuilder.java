package io.dh.spring.connectit.testutils;

import io.dh.spring.connectit.testutils.testdata.DataGenerator;
import io.dh.spring.connectit.testutils.testdata.DataGenerators;

import java.lang.reflect.Field;
import java.util.Arrays;


public class TestDataBuilder {

    /**
     * todo: enhance the random data creation
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T  buildRandomData(Class<T> clazz){
      return buildRandomData(clazz,"");
    }

    public static <T> T  buildRandomData(Class<T> clazz,String...ignoreFields){
        T instance = DHReflections.instanceOf(clazz);
        for (Field field : DHReflections.fieldsOf(clazz)) {
            if(Arrays.binarySearch(ignoreFields,field.getName().toLowerCase())>=0) continue;
            if(DHReflections.getFieldValue(field,instance)!=null) continue;
            DataGenerator generator = DataGenerators.getGeneratorByType(field.getType());
            Object randomValue = generator.generate(field.getName());
            DHReflections.setFieldValue(instance,field,randomValue);
        }
        return instance;
    }

    public static <T> T  buildRandomDataWOId(Class<T> clazz){
        return buildRandomData(clazz,"id");
    }


}
