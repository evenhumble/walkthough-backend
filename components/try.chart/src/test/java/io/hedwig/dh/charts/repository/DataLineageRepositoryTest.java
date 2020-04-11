package io.hedwig.dh.charts.repository;


import io.hedwig.dh.charts.BaseDAOTest;
import io.hedwig.dh.charts.model.DataLineage;
import io.hedwig.dh.charts.utils.MockUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;


@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(NAME_ASCENDING)
public class DataLineageRepositoryTest extends BaseDAOTest {


    private static DataLineage mockDL = MockUtils.mockDataLineage();
    @Autowired
    private DataLineageRepository dataLineageRepository;

    @Test
    @Transactional
    public void a_saveDataLineageData(){
        mockDL = dataLineageRepository.save(mockDL);
        Assert.assertNotNull(mockDL.getId());

    }

    @Test
    @Transactional
    public void b_findByTableName_intValidTableName() {
        DataLineage dl =  dataLineageRepository.findByTableName(this.mockDL.getTableName());
        Assert.assertNotNull(dl);
    }

    @Test
    @Transactional
    public void c_findByDLId() {
        DataLineage dl =  dataLineageRepository.getOne(3);
        Assert.assertNotNull(dl);
    }
}