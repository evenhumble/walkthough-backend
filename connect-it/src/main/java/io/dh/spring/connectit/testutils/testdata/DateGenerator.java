package io.dh.spring.connectit.testutils.testdata;

import java.util.Date;
import java.util.Random;

public class DateGenerator implements DataGenerator<Date> {

    public Date generate(Object rule) {

        return new Date();
    }
}
