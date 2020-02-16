package io.qkits.corejava.corejava.guavasamples;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestFunction {

    @Test
    public void testMap() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        List<Integer> list1 = list.stream().map(k -> k * 2).collect(Collectors.toList());

        System.out.println(list1.get(2));

        list.forEach(System.out::println);

        System.out.println(list.stream().map(k->k*10).reduce((sum, i) -> sum + i).get());

    }

}
