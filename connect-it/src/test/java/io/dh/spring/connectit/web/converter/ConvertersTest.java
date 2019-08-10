package io.dh.spring.connectit.web.converter;

import io.dh.spring.connectit.domain.Project;
import io.dh.spring.connectit.testutils.BaseDAOTest;
import io.dh.spring.connectit.testutils.TestDataBuilder;
import io.dh.spring.connectit.web.dto.ProjectDTO;
import org.junit.Assert;
import org.junit.Test;


public class ConvertersTest extends BaseDAOTest {

    @Test
    public void genericConvertTest() {
        Project project =  TestDataBuilder.buildRandomData(Project.class);
        ProjectDTO projectDTO = Converters.convert(
               project,ProjectDTO.class
        );

        Assert.assertEquals(projectDTO.getProjectName(),project.getProjectName());
    }
}