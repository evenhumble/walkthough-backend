package io.hedwig.dh.charts.web.dto.converters;

import io.hedwig.dh.charts.web.dto.DBField;
import io.hedwig.dh.charts.web.dto.DataLineageDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataLineageConverterTest {
    DataLineageConverter converter = new DataLineageConverter();
    @Test
    public void covertToDBTable() {

    }

    @Test
    public void beanToJsonString() {

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
       String jsonString=  converter.beanToJsonString(dto);
        System.out.println(jsonString);
    }
}