package io.hedwig.modules.redis;



import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author: patrick
 */
public class HRedisCommandsTest {

    @Test
    public void set() throws Exception {

        HRedis.set("test_key", "test_object");
        String result = HRedis.get("test_key");
        Assert.assertEquals(result,"test_object");
    }

    @Test
    public void test_sadd(){
        HRedis.sadd("multiple_value","test1","test2","test3");
        Set<String> result = HRedis.smembers("multiple_value");
        System.out.println(result);
    }

    @Test
    public void test_append(){
        HRedis.append("book","appended_book");
        String result = HRedis.get("book");
        Assert.assertThat(result, CoreMatchers.containsString("appended_book"));
    }

    @Test
    public void test_rpush(){
        HRedis.rpush("rpush_key","rpush_value","rpush_value_1","rpush_value_2");
        List<String> result = HRedis.getAllDequeList("rpush_key");
        Assert.assertTrue(result.size()>9);
    }

    @Test
    public void test_flushdb(){
        HRedis.set("book","test_book");
        HRedis.flushDB();
        String result = HRedis.get("book");
        Assert.assertTrue(result==null);
    }

}
