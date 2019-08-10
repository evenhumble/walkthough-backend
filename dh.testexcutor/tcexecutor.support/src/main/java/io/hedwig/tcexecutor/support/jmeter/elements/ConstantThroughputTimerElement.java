package io.hedwig.tcexecutor.support.jmeter.elements;

import lombok.Builder;
import lombok.Getter;
import org.apache.jmeter.testbeans.gui.TestBeanGUI;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.timers.ConstantThroughputTimer;

@Builder
@Getter
public class ConstantThroughputTimerElement extends JMeterStepImpl<ConstantThroughputTimerElement> {
    private int throughput;
    private ConstantThroughputTimerCalcMode calcMode;

    public TestElement getTestElement() {
        ConstantThroughputTimer timer = new ConstantThroughputTimer();
        timer.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());
        timer.setProperty(TestElement.TEST_CLASS, ConstantThroughputTimer.class.getName());
        timer.setName("Constant Throughput Timer");
        timer.setComment("Used to throttle the amount of activity in any given minute to simulate real user loads");
        timer.setEnabled(true);

        // calling the setters doesn't work in jmeter 2.11
        timer.setProperty("throughput", throughput);
        timer.setProperty("calcMode", calcMode.getIndex());

        return timer;
    }

}
