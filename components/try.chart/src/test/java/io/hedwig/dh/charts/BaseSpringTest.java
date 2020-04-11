package io.hedwig.dh.charts;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest(
        includeFilters = @ComponentScan.Filter(Repository.class)
)
@SpringBootTest(classes = TryChartApplication.class)
public class BaseSpringTest {
}
