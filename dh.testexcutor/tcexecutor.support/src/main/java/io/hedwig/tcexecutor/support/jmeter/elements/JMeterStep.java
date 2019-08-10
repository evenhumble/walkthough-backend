package io.hedwig.tcexecutor.support.jmeter.elements;

import org.apache.jmeter.testelement.TestElement;

import java.util.List;

public interface JMeterStep {
    <T extends TestElement> T getTestElement();

    List<JMeterStep> getSteps();

    boolean isReportable();

    void setReportable(boolean value);

    void setOutputFilePath(String userFileName);
    
}
