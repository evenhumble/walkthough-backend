package io.qmeta.jpa.demos.generator.entity.msyql;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableId implements Serializable {
    private String tableSchema;
    private String tableName;
}
