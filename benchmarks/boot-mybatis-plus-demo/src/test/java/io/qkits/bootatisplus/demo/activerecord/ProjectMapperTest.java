package io.qkits.bootatisplus.demo.activerecord;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.qkits.bootatisplus.demo.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import io.qkits.bootatisplus.demo.project.entity.Project;
import io.qkits.bootatisplus.demo.project.mapper.ProjectMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: patrick on 2020/1/4
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ProjectMapperTest {

    @Resource
    private ProjectMapper pm;

    @BeforeEach
    public void testInsetProject() {
        System.out.println("start to insert data");
        Project p = new Project();
        p.setId(5L).setParentId(1L)
                .setProjectName("Project Test Name1")
                .setProjectSummary("project summary");
        pm.insert(p);

        Project inserted = pm.selectById(p.getId());
        Assertions.assertNotNull(inserted);
    }

    @Test
    public void testUpdateProject() {
        Project p = new Project();
        p.setId(5L).setProjectName("changed Name")
                .setProjectSummary("changed summary");
        int count = pm.updateById(p);
        Assertions.assertEquals(count, 1);
        Project selectedP = pm.selectById(5L);
        Assertions.assertEquals(selectedP.getProjectName(), "changed Name");
        Assertions.assertEquals(selectedP.getProjectSummary(), "changed summary");

    }

    @Test
    //ToDo: understand more about the Wrappers logic and lambaUpdate Logic,
    //it is kind of too complex to understand, it might be not a good practice in reality
    //plain sql with conditions in Mapper file might be good enough
    public void testCRUDInOneCase(){
        Project p = new Project();
        p.setProjectSummary("Project1").setProjectSummary("Project1Summary")
                .setParentId(0L);

        p.insert();
        System.out.println(p.getId());
        Project anotherProject = pm.selectById(p.getId());
        Project oneProject = pm.selectOne(Wrappers.lambdaQuery(p));
        Assertions.assertEquals(anotherProject.getProjectSummary(),oneProject.getProjectSummary());
        pm.update(new Project(),Wrappers.<Project>lambdaUpdate()
                .set(Project::getProjectSummary,"changed-one")
                .eq(Project::getId,2));
        oneProject.deleteById();
        Project afterDelete = pm.selectById(p.getId());
        Assertions.assertNull(afterDelete);

        pm.selectList(Wrappers.<Project>lambdaQuery()
        .select(Project::getId)).forEach(System.out::println);

        pm.selectList(new QueryWrapper<Project>()
                .select("id","project_name")).forEach(System.out::println);

        pm.selectList(new QueryWrapper<>()).forEach(System.out::println);
    }

    @Test
    public void testWithQueryMap(){
        List<Map<String,Object>> resultList = pm.selectMaps(Wrappers.<Project>query()
                .orderByAsc("id"));
        resultList.forEach(System.out::println);

    }

    @Test
    public void testWithPage(){
        IPage<Map<String,Object>> page =
                pm.selectMapsPage(new Page<>(1,2),new QueryWrapper<>());
        System.out.println(page);

        IPage<Project> entityPage = pm.selectPage(new Page<Project>(1,2),
                new QueryWrapper<>());
        System.out.println(entityPage);
    }
}