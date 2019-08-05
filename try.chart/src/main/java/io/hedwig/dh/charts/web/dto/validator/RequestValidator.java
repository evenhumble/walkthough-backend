package io.hedwig.dh.charts.web.dto.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RequestValidator {

    public <T> void requireNotNull(T...objects){
        for (T object : objects) {
            Objects.requireNonNull(object);
        }
    }
}
