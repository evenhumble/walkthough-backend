package io.dh.spring.connectit.testutils.testdata;



import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class DataGenerators {

    public static DataGenerators getInstance() {
        return new DataGenerators();
    }

    static Map<Class, DataGenerator> generators = Maps.newConcurrentMap();
    static{
        generators.put(Integer.class,new IntegerGenerator());
        generators.put(String.class,new StringGenerator());
        generators.put(Date.class,new DateGenerator());
    }
    public static DataGenerator getGeneratorByType(Class type){
        return generators.getOrDefault(type,new StringGenerator());
    }

}
