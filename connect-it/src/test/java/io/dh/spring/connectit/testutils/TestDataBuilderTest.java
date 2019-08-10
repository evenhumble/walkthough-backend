package io.dh.spring.connectit.testutils;

import io.dh.spring.connectit.testentity.BaseEntityForTest;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class TestDataBuilderTest {

    @Test
    public void testBuildRandomData() {

        BaseEntityForTest entityForTest = TestDataBuilder.buildRandomData(BaseEntityForTest.class);
        assertNotNull(entityForTest.getName());
    }
}