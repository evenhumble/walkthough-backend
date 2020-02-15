package io.qdriven.plugins.redis.commands;

import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

import io.qdriven.plugins.redis.exceptions.RedisCommandException;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by patrick on 16/8/17.
 */
public class RedisCommandExecutor implements RedisExecutor {

    private final static Map<String,Method> redisCommandMap = new HashMap<>();

    static {
        for (Method method : Jedis.class.getDeclaredMethods()) {
            redisCommandMap.put(method.getName(),method);
        }
    }

    public static void main(String[] args) {
        System.out.println(RedisCommandExecutor.redisCommandMap);
    }

    @Override
    public Object execute(JavaSamplerContext context,Jedis client) {
        String commandName = context.getParameter("command");
        String key=context.getParameter("key");
        String value = context.getParameter("value");

        Method m = redisCommandMap.get(commandName);
        try {
            //todo change for different redis command, this method only support very simple redis commands
            if(StringUtils.isNotBlank(value)){
                return m.invoke(client,key,value);
            }else{
                return m.invoke(client,key);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RedisCommandException(e)  ;
        }
    }
}
