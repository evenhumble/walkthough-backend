package io.qmeta.jpa.demos.generator.repository;

import io.qmeta.jpa.demos.generator.entity.msyql.TableDO;
import io.qmeta.jpa.demos.generator.entity.msyql.TableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepo extends JpaRepository<TableDO, TableId> {

    List<TableDO> findByTableName(String tableName);
    TableDO findByTableSchemaAndTableName(String tableSchema,String tableName);

}
