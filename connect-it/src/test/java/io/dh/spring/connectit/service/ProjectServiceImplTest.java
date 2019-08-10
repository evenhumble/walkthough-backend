package io.dh.spring.connectit.service;

import io.dh.spring.connectit.common.entity.TreeEntityUtil;
import io.dh.spring.connectit.domain.Project;
import io.dh.spring.connectit.service.intf.ProjectService;
import io.dh.spring.connectit.testutils.BaseDAOTest;
import io.dh.spring.connectit.testutils.BaseSpringTest;
import io.dh.spring.connectit.testutils.TestDataBuilder;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Rollback(value = false)
public class ProjectServiceImplTest extends BaseSpringTest {

    @Autowired
    ProjectService projectService;

    @Test
    public void createProject() {
        Project project = TestDataBuilder.buildRandomData(Project.class);
        projectService.createProject(project);
    }

    @Test
    public void updateProject() {

        Project project = createEntity();
        project.setProjectName("testProjectName");
        projectService.updateProject(project);
        Project projectUpdated = projectService.getProjectById(project.getId()).get();
        Assert.assertEquals(projectUpdated.getProjectName(),"testProjectName");
    }

    @Test
    public void listProjects() {
    }

    @Test
    public void getProjectById() {
       Optional<Project> projectOption = projectService.getProjectById(0);
       Assert.assertFalse(projectOption.isPresent());
    }

    @Test
    public void getSubProjects() {
        List<Project> subProjects = projectService.getSubProjects(TreeEntityUtil.dummyRootEntity(Project.class));
        Assert.assertTrue(subProjects.size()>=1);
    }

    @Test
    public void deactiveProject() {
        Project project = createEntity();
        projectService.deactiveProject(project.getId());
        Project project1 = projectService.getProjectById(project.getId()).get();
        Assert.assertEquals(project1.getStatus(),0);
    }

    @Test
    public void activeProject() {

        Project project = createEntity();
        projectService.activeProject(project.getId());
        Project project1 = projectService.getProjectById(project.getId()).get();
        Assert.assertEquals(project1.getStatus(),1);
    }

    private Project createEntity() {
        Project project = TestDataBuilder.buildRandomDataWOId(Project.class);
        projectService.createProject(project);
        return project;
    }
}