package io.qmeta.jpa.demos.generator.domain.java;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
public class EntityObject {
    private String tableName;
    private String className;
    private String classComment;
    private List<FieldObject> fields;
}
