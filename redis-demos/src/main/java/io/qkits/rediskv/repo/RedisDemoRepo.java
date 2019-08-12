package io.qkits.rediskv.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author: patrick on 2019-08-11
 * @Description: redist get value: 1. get/set key with class or a format 2. get key by a json format
 * beforeRedisOp -> RedisOp -> afterRedisOp
 */
@Repository
public class RedisDemoRepo {

  @Autowired
  private StringRedisTemplate sRedisTemplate;

  public void set(String key, String value) {
    ValueOperations<String, String> ops = sRedisTemplate.opsForValue();
    ops.set(key, value);
  }

  public String get(String key) {
    ValueOperations<String, String> ops = this.sRedisTemplate.opsForValue();
    return ops.get(key);
  }
}
