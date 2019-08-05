package io.hedwig.tcexecutor.support.jmeter.elements;

import com.google.common.base.Preconditions;
import lombok.Builder;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.testelement.TestElement;

@Builder
public class LoopElement extends JMeterStepImpl<LoopElement> {

    private String name;
    private Integer loopCount;
    private Boolean continueForever;
    private Boolean isFirst;

    // for cases where it's a test element
    public TestElement getTestElement() {
        return getLoopController();
    }

    // for cases where it's pulled in to a ThreadGroupElement
    public LoopController getLoopController() {
        Preconditions.checkNotNull(name);
        LoopController loopController = new LoopController();
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.NAME, name);
        loopController.setProperty(TestElement.ENABLED, true);
        loopController.setLoops(getOptionalValue(loopCount, 1));
        loopController.setContinueForever(getOptionalValue(continueForever, false));
        loopController.setFirst(getOptionalValue(isFirst, true));
        return loopController;
    }
}
