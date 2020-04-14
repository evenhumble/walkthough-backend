package io.qmeta.jpa.demos.generator.domain.java;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ClassObject {
    private String className;
    private String classComment;
    private List<FieldObject> fields;
}
