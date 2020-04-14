package io.qmeta.jpa.demos.generator.entity.msyql;

import lombok.Data;

import javax.persistence.*;
//import javax.persistence.Entity;


@Entity
@Data
@Table(name = "columns")
@IdClass(ColumnId.class)
public class Column {
    private String tableCatalog;
    @Id
    private String tableSchema;
    @Id
    private String tableName;
    @Id
    private String columnName;
    private Integer ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private Long characterMaximumLength;
    private Long characterOctetLength;
    private Long numericPrecision;
    private Long numericScale;
    private Integer datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;
    private String generationExpression;
    private Integer srsId;
}
