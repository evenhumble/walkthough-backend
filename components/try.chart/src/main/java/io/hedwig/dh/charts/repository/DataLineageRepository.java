package io.hedwig.dh.charts.repository;

import io.hedwig.dh.charts.model.DataLineage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DataLineageRepository extends JpaRepository<DataLineage,Integer> {
    //this is HQL
//    @Query("from DataLineage where tableName =:tableName" +
//            " and active =1")
    @Query(name = "findByTableName",
            value = "select * from data_lineage where table_name=:tableName and active=1"
    ,nativeQuery = true)
    DataLineage findByTableName(@Param("tableName") String tableName);


    @Query(name = "findByDBTableName",value = "select * from data_lineage where db_name=:dbName and table_name=:tableName and active=1"
            ,nativeQuery = true)
    DataLineage findByDBTableName(@Param("dbName") String dbName,@Param("tableName") String tableName);
}
