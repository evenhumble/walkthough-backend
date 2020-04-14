package io.qmeta.jpa.demos.generator.entity.msyql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tables")
@IdClass(TableId.class)
public class TableDO {
    private String tableCatalog;
    @Id
    private String tableSchema;
    @Id
    private String tableName;
    private String tableType;
    private String engine;
    private Integer version;
    private String rowFormat;
    private Long tableRows;
//    private Long avgRow;
//    private Long dataLength;
//    private Long maxData;
    private Long indexLength;
    private Long dataFree;
    private Long autoIncrement;
    private Date createTime;
    private Date updateTime;
    private Date checkTime;
    private String tableCollation;
    private Long checksum;
    private String createOptions;
    private String tableComment;
}

