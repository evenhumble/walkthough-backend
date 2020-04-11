package io.qdriven.plugins.redis.commands;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.threads.JMeterContext;
import redis.clients.jedis.Jedis;

/**
 * Created by patrick on 16/8/12.
 */
public interface RedisExecutor{

    Object execute(JavaSamplerContext context, Jedis clients);
}
