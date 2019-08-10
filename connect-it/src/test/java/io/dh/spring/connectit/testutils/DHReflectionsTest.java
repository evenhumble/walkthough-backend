package io.dh.spring.connectit.testutils;

import io.dh.spring.connectit.testentity.BaseEntityForTest;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class DHReflectionsTest {

    @Test
    public void testSetFieldValue() {
        BaseEntityForTest entityForTest = new BaseEntityForTest();
        Field[] fields = DHReflections.fieldsOf(entityForTest.getClass());
        for (Field field : fields) {
            DHReflections.setFieldValue(entityForTest,field,"value");
        }

        assertEquals(entityForTest.getName(),"value");
    }

    @Test
    public void testInstanceOf() {

        assertNotNull(DHReflections.instanceOf(BaseEntityForTest.class));

    }


    @Test
    public void testFieldsOf() {

        Field[] fields = DHReflections.fieldsOf(BaseEntityForTest.class);
        assertTrue(fields.length>1);
    }
}