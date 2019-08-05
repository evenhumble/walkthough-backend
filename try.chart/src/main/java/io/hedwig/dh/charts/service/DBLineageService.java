package io.hedwig.dh.charts.service;

import io.hedwig.dh.charts.model.DataLineage;
import io.hedwig.dh.charts.repository.DataLineageRepository;
import io.hedwig.dh.charts.web.dto.DBField;
import io.hedwig.dh.charts.web.dto.DataLineageDTO;
import io.hedwig.dh.charts.web.dto.converters.DataLineageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBLineageService {

    @Autowired
    private DataLineageRepository repository;

    @Autowired
    private DataLineageConverter converter;

    public DataLineageDTO getDataLineageService(String dbName, String tableName){
        DataLineage dataLineage =  repository.findByDBTableName(dbName,tableName);
        if(dataLineage!=null && dataLineage.getId()!=null){
            return converter.covertToDataLineageDTO(dataLineage);
        }
        return new DataLineageDTO();
    }

    public DataLineageDTO getMockDL(String dbName,String tableName){
        DataLineageDTO dto = new DataLineageDTO();
        dto.setDbName("tmp");
        dto.setName("tmp");
        List<DBField> dbFields = new ArrayList<DBField>();
        for (int i = 0; i < 10; i++) {
            DBField field = new DBField();
            field.setName("tmp"+i);
            field.setDbName("tmp"+i);
            field.setTableName("tmp_table"+i);
            List<DBField> fields = new ArrayList<DBField>();
            for (int j = 0; j < 4; j++) {
                DBField field1 = new DBField();
                field1.setName("second_"+j);
                field1.setDbName("second_"+j);
                field1.setTableName("second_"+j);
                fields.add(field1);
            }
            dbFields.add(field);
            field.setChildren(fields);
        }
        dto.setChildren(dbFields);
        return dto;
    }
}
