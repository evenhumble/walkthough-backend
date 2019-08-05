package io.dh.spring.connectit.common;


import io.dh.spring.connectit.web.dto.ProjectDTO;
import org.junit.Test;

import javax.validation.ValidationException;

import static org.junit.Assert.assertNotNull;

public class DHValidatorsTest {


    @Test(expected = NullPointerException.class)
    public void testVerifyNotNull() {
        DHValidators.validator().verifyNotNull(null);
    }

    @Test(expected =NullPointerException.class )
    public void testVerifyNotNull1() {
        DHValidators.validator().verifyNotNull(
                null,"object is null"
        );
    }

    @Test
    public void testValidator() {

        assertNotNull(DHValidators.validator());
    }

    @Test(expected = ValidationException.class)
    public void testValidateWithViolations(){
        ProjectDTO dto = new ProjectDTO();
        DHValidators.validateEntity(dto);
    }
}