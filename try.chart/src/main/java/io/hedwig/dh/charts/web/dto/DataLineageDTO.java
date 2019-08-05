package io.hedwig.dh.charts.web.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class DataLineageDTO {

    @JSONField(name = "name")
    private String name;
    @JSONField(name = "db_name")
    private String dbName;
    @JSONField(name = "children")
    private List<DBField> children = new ArrayList<DBField>();

    public List<DBField> getChildren() {
        return children;
    }

    public void setChildren(List<DBField> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
