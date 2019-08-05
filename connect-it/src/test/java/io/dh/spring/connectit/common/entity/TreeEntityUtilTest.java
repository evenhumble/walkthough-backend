package io.dh.spring.connectit.common.entity;

import io.dh.spring.connectit.domain.Project;
import io.dh.spring.connectit.testutils.BaseDAOTest;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeEntityUtilTest{

    @Test
    public void dummyRootEntity() {
        Project project = TreeEntityUtil.dummyRootEntity(Project.class);
        Assert.assertEquals(project.getId(),new Integer(0));
    }
}