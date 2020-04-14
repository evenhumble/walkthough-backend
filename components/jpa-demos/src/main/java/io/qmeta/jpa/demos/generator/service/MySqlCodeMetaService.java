package io.qmeta.jpa.demos.generator.service;

import io.qmeta.jpa.demos.generator.domain.java.*;
import io.qmeta.jpa.demos.generator.entity.msyql.Column;
import io.qmeta.jpa.demos.generator.entity.msyql.TableDO;
import io.qmeta.jpa.demos.generator.repository.ColumnRepo;
import io.qmeta.jpa.demos.generator.repository.TableRepo;
import io.qmeta.jpa.demos.generator.support.MysqlTypeMapping;
import io.qmeta.jpa.demos.generator.utils.NamingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MySqlCodeMetaService implements CodeMetaService {

    @Autowired
    private ColumnRepo columnRepo;
    @Autowired
    private TableRepo tableRepo;

    @Override
    public EntityObject buildEntityClass(String tableName) {
        TableDO table = tableRepo.findByTableName(tableName).get(0);
        List<Column> colObjects = columnRepo.findByTableName(tableName);
        List<FieldObject> fieldObjects = new ArrayList<>();
        for (Column columnObject : colObjects) {
            fieldObjects.add(
                    FieldObject.builder().fieldName(
                            NamingUtils.convertToJavaName(columnObject.getColumnName())
                    ).fieldType(MysqlTypeMapping.getJavaTypeByDDLType(columnObject.getColumnType()))
                            .fieldComment(columnObject.getColumnComment()).build()
            );
        }
        EntityObject entityObject = new EntityObject();
        entityObject.setClassName(NamingUtils.convertToJavaName(tableName));
        entityObject.setClassComment(table.getTableComment());
        entityObject.setFields(fieldObjects);
        return entityObject;
    }

    @Override
    public ServiceObject buildServiceClass(String tableName) {
        return ServiceObject.builder().className(NamingUtils.convertToJavaName(tableName))
                .build();
    }

    @Override
    public ControllerObject buildControllerClass(String tableName) {

        return ControllerObject.builder().className(NamingUtils.convertToJavaName(tableName))
                .requestUrl(tableName.toLowerCase())
                .build();
    }

    @Override
    public RepositoryObject buildRepositoryClass(String tableName) {
        return RepositoryObject.builder().className(
                NamingUtils.convertToJavaName(tableName)
        ).build();
    }
}