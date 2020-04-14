package io.qmeta.jpa.demos.generator.domain.java;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldObject {
    private String fieldName;
    private String fieldType;
    private String fieldComment;
}
