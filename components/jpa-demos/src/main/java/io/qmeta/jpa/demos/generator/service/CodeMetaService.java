package io.qmeta.jpa.demos.generator.service;

import io.qmeta.jpa.demos.generator.domain.java.*;
import io.qmeta.jpa.demos.generator.utils.NamingUtils;
import org.springframework.stereotype.Service;

@Service
public interface CodeMetaService {

    public EntityObject buildEntityClass(String tableName);

    public ServiceObject buildServiceClass(String tableName);

    public default ControllerObject buildControllerClass(String tableName) {
        return ControllerObject.builder().className(NamingUtils.convertToJavaName(tableName))
                .requestUrl(tableName.toLowerCase())
                .build();
    }

    public default RepositoryObject buildRepositoryClass(String tableName) {
        return RepositoryObject.builder().className(
                NamingUtils.convertToJavaName(tableName)
        ).build();
    }
}
