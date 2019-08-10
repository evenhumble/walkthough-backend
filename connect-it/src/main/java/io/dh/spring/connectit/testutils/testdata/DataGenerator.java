package io.dh.spring.connectit.testutils.testdata;

public interface DataGenerator<T> {

    T generate(Object rule);
}
