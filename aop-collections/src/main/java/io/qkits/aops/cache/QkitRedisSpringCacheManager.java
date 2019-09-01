package io.qkits.aops.cache;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;

/**
 * @author: patrick on 2019-08-24
 * @Description:
 */

public class QkitRedisSpringCacheManager extends RedissonSpringCacheManager {

  private long cacheTimeout = 0L;

  public QkitRedisSpringCacheManager(RedissonClient redisson) {
    super(redisson);
  }

  @Override
  protected CacheConfig createDefaultConfig() {
    CacheConfig cacheConfig = new CacheConfig();

    cacheConfig.setMaxIdleTime(cacheTimeout);
    return cacheConfig;
  }


  public long getCacheTimeout() {
    return cacheTimeout;
  }

  public void setCacheTimeout(long cacheTimeout) {
    this.cacheTimeout = cacheTimeout;
  }
}
