package io.qkits.corejava.corejava.nio.example;

import java.nio.CharBuffer;

/**
 * User: mazhqa
 * Date: 13-11-21
 */
public class CharBufferTest {

    public static void main(String[] args){
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println(String.format("capacity: %s, limit: %s, position: %s", buffer.capacity(), buffer.limit(), buffer.position()));
        buffer.put("a");
        buffer.put("b");
        buffer.put("c");
        buffer.put("c");
        buffer.put("c");
        buffer.put("c");
        System.out.println(String.format("After add a, b,c the capacity: %s, limit: %s, position: %s", buffer.capacity(), buffer.limit(), buffer.position()));
        buffer.flip();
        System.out.println(String.format("After flip the capacity: %s, limit: %s, position: %s", buffer.capacity(), buffer.limit(), buffer.position()));
        System.out.println(String.format("The first element is :%s", buffer.get()));
        System.out.println(String.format("After get the first element the capacity: %s, limit: %s, position: %s", buffer.capacity(), buffer.limit(), buffer.position()));
        buffer.clear();
        System.out.println(String.format("After clear the capacity: %s, limit: %s, position: %s", buffer.capacity(), buffer.limit(), buffer.position()));
        System.out.println(String.format("The third element is :%s", buffer.get(2)));
        System.out.println(String.format("After get the third element the capacity: %s, limit: %s, position: %s", buffer.capacity(), buffer.limit(), buffer.position()));

    }

}
