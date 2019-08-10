package io.dh.spring.connectit.testutils.testdata;

import java.util.Random;

public class IntegerGenerator implements DataGenerator<Integer> {

    public Integer generate(Object rule) {

        return new Random().nextInt(100);
    }
}
