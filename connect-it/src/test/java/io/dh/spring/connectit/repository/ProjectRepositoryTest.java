package io.dh.spring.connectit.repository;

import io.dh.spring.connectit.domain.Project;
import io.dh.spring.connectit.testutils.BaseDAOTest;
import io.dh.spring.connectit.testutils.TestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ProjectRepositoryTest extends BaseDAOTest {

    @Autowired
    private ProjectRepository projectRepository;

    static Project project;

    @Before
    public void setupProjectData(){
      project=  TestDataBuilder.buildRandomData(Project.class,"id","parentId");
      projectRepository.save(project);
    }

    @Test
    public void findProjectsByParentId() {
        List<Project> projects = projectRepository.findProjectsByParentId(project.getId());
        Assert.assertEquals(projects.size(),0);
    }

    @Test
    public void findProjectsByParentId_withValue(){
        List<Project> projects = projectRepository.findProjectsByParentId(0);
        Assert.assertTrue(projects.size()>=1);
    }

    @Test
    public void updateProject(){
        project.setParentId(0);
        project.setStatus(1);
        projectRepository.save(project);
    }
}