package io.hedwig.dh.charts.web.dto.converters;

import com.alibaba.fastjson.JSON;
import io.hedwig.dh.charts.web.dto.DBField;
import io.hedwig.dh.charts.web.dto.DataLineageDTO;
import io.hedwig.dh.charts.model.DataLineage;
import io.hedwig.dh.charts.utils.JSONUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLineageConverter {

    public DataLineageDTO covertToDataLineageDTO(DataLineage dl){
//        DataLineageDTO dbLineageDTO = new DataLineageDTO();
//        dbLineageDTO.setDbName(dl.getDbName());
//        List<DBField> dbFields = JSONUtil.convertToListBean(dl.getDataLineageJson(),
//                DBField.class);
//        dbLineageDTO.setChildren(dbFields);
        DataLineageDTO dbLineageDTO  = JSONUtil.convertToBean(dl.getDataLineageJson(),DataLineageDTO.class);
        return dbLineageDTO;
    }

    public <T> String beanToJsonString(T instance){
        return JSON.toJSONString(instance);
    }
}
