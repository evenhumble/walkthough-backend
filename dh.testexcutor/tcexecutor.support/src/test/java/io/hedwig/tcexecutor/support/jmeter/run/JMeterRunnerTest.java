package io.hedwig.tcexecutor.support.jmeter.run;

import io.hedwig.tcexecutor.support.jmeter.elements.JMeterThreadGroup;
import org.junit.Test;

import static org.junit.Assert.*;

public class JMeterRunnerTest {

    @Test
    public void testRunJMeter() {
        JMeterRunner jMeter = new JMeterRunner("Login");
        jMeter.addStep(JMeterThreadGroup.create("Login Thread Group")
        .addStep(HttpSteps.baiduSearch()));
        jMeter.createJMX();

    }
}