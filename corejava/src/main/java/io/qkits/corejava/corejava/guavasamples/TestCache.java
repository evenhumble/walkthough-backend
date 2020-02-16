package io.qkits.corejava.corejava.guavasamples;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;

public class TestCache {

    static int count = 0;

    @Test
    public void testCache() throws ExecutionException {

        LoadingCache<String,String> cahceBuilder=CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>(){
                    @Override
                    public String load(String key) throws Exception {
                        String strProValue="hello "+key+"!";
                        count++;
                        return strProValue;
                    }

                });
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
        System.out.println(cahceBuilder.get("jerry") + "  " + count);
    }
}
