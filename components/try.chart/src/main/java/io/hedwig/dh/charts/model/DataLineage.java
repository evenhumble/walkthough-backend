package io.hedwig.dh.charts.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "data_lineage")
public class DataLineage {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "db_name")
    private String dbName;
    @Column(name = "data_lineage_json")
    private String dataLineageJson;
    @Column(name = "active")
    private int active;
    @Column(name = "created_time")
    private Date createdTime = new Date();
    @Column(name="updated_time")
    private Date updatedTime = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDataLineageJson() {
        return dataLineageJson;
    }

    public void setDataLineageJson(String dataLineageJson) {
        this.dataLineageJson = dataLineageJson;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
