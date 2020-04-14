package io.qmeta.jpa.demos.generator.repository;

import io.qmeta.jpa.demos.generator.entity.msyql.Column;
import io.qmeta.jpa.demos.generator.entity.msyql.ColumnId;
import io.qmeta.jpa.demos.generator.entity.msyql.TableDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepo extends JpaRepository<Column, ColumnId> {

    public List<Column> findByTableName(String tableName);
}
