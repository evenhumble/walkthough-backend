package io.qmeta.jpa.demos.generator.domain.java;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerObject   {
    private String className;
    private String requestUrl ;
}
