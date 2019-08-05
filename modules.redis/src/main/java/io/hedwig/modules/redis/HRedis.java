package io.hedwig.modules.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.ShardedJedis;

/**
 * @author: patrick
 */
public class HRedis {

    private static Jedis jedis = new Jedis("localhost",6379);
//    private static JedisCommands commands = new JedisCluster()
    /**
     * SDS: Simple Dynamic String,REDIS几乎都是用SDS，主要作用如下：
     * 1. StringObject
     * 2. char* 替代品
     *
     * set key,value pair into redis
     * @param key
     * @param value
     */
    public static void set(String key,String value) {
        jedis.set(key,value);
    }

    /**
     * Get value by given key
     * @param key
     * @return
     */
    public static String get(String key) {
        return jedis.get(key);
    }

    /**
     * add set values, sadd
     * @param key
     * @param args
     */
    public static void sadd(String key,String...args){
        jedis.sadd(key,args);
    }

    /**
     * get set members from the keys
     * @param key
     * @return
     */
    public static Set<String> smembers(String key){
        return jedis.smembers(key);
    }

    public static void append(String key,String appendedValue){
        jedis.append(key,appendedValue);
    }

    /**
     * rpush to a key
     * 双端列表
     * @param key
     * @param values
     */
    public static void rpush(String key,String ... values){
        jedis.rpush(key,values);
    }

    public static List<String> lrange(String key, int start, int end){
        return jedis.lrange(key,start,end);
    }

    /**
     * get all values of deque list
     * @param key
     * @return
     */
    public static List<String> getAllDequeList(String key){
        return jedis.lrange(key,0,-1);
    }

    /**
     * clear all the key/value pair
     */
    public static void flushDB(){
        jedis.flushDB();
    }

    /**
     * hset usage
     * @param key
     * @param field
     * @param value
     */
    public static void hset(String key,String field,String value){
        jedis.hset(key,field,value);
    }
}
