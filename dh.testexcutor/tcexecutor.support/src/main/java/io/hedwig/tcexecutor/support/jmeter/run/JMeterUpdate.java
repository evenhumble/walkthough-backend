package io.hedwig.tcexecutor.support.jmeter.run;

import lombok.Data;

@Data
public class JMeterUpdate {
    private JMeterStatus state;
    private String testPlanName;
}
