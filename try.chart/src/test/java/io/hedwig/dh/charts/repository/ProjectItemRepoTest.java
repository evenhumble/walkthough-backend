package io.hedwig.dh.charts.repository;

import io.hedwig.dh.charts.BaseDAOTest;
import io.hedwig.dh.charts.model.ProjectItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ProjectItemRepoTest extends BaseDAOTest {

    @Autowired
    ProjectItemRepo repo;
    @Test
    @Transactional
    public void testSaveItem(){
        ProjectItem pi = new ProjectItem();
        pi.setName("test");
        pi.setDescription("test");
        pi.setParentId(0L);
        pi.setActive(1);
        repo.save(pi);

        Assert.assertNotNull(pi.getId());

    }
}