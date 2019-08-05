package io.dh.spring.connectit.testutils;

import io.dh.spring.connectit.ConnectItApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConnectItApplication.class)
public class BaseSpringTest {
    @Test
    public void testEmpty(){

    }
}
