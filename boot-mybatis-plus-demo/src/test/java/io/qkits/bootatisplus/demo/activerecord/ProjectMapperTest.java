package io.qkits.bootatisplus.demo.activerecord;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import io.qkits.bootatisplus.demo.project.entity.Project;
import io.qkits.bootatisplus.demo.project.mapper.ProjectMapper;

/**
 * @author: patrick on 2020/1/4
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectMapperTest {

  @Resource
  private ProjectMapper pm;

  @Test
  public void testInsetProject(){
    Project p = new Project();
    p.setId(5L).setParentId(1L)
        .setProjectName("Project Test Name1")
        .setProjectSummary("project summary");
    pm.insert(p);

    Project inserted= pm.selectById(p.getId());
    Assert.assertNotNull(inserted);
  }
}