package io.qkits.corejava.corejava.aopsamples.cflowsamples;

import org.junit.Test;

/**
 * Created by patrick on 16/6/14.
 */
public class TestCFlow {

    public void foo(){
        System.out.println("Foo........");
    }

    public void bar(){
        System.out.println("bar.........");
    }

    @Test
    public void testMethod(){
        bar();
        foo();
    }
}
