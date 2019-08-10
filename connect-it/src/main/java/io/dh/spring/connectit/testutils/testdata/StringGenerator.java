package io.dh.spring.connectit.testutils.testdata;

public class StringGenerator implements DataGenerator<String> {

    public String generate(Object rule) {

        return rule.toString()+System.currentTimeMillis();
    }
}
