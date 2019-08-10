package io.hedwig.dh.charts.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    public static <T> T convertToBean(String json, Class<T> clazz){

       return JSON.parseObject(json,clazz);
    }

    public static <T> List<T> convertToListBean(String json, Class<T> clazz){

       Type[] types = new Type[1];
       types[0]=clazz.getComponentType();
       return (List<T>) JSON.parseArray(json,types);
    }
}
