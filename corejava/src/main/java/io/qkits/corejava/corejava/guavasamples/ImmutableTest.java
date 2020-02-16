package io.qkits.corejava.corejava.guavasamples;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

public class ImmutableTest {

    @Test
    public void testImmutable() {
        ImmutableMap<String, String> immutableMap = ImmutableMap.of("1", "1234");

        System.out.println(immutableMap.get("1"));
    }
}
