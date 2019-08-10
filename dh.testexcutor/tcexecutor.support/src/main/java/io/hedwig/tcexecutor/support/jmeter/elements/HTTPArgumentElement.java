package io.hedwig.tcexecutor.support.jmeter.elements;

import com.google.common.base.Preconditions;
import lombok.Builder;
import org.apache.jmeter.protocol.http.gui.HTTPArgumentsPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.testelement.TestElement;

@Builder
public class HTTPArgumentElement extends JMeterStepImpl<HTTPArgumentElement> {
    private String name;
    private String value;
    private String metaData;
    private Boolean useEquals;
    private Boolean setAlwaysEncoded;

    public TestElement getTestElement() {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(value);

        HTTPArgument httpArgument = new HTTPArgument();
        httpArgument.setProperty(TestElement.GUI_CLASS, HTTPArgumentsPanel.class.getName().toString());
        httpArgument.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName().toString());
        httpArgument.setName(name);
        httpArgument.setValue(value);
        httpArgument.setMetaData(getOptionalValue(metaData, "="));
        httpArgument.setUseEquals(getOptionalValue(useEquals, true));
        httpArgument.setAlwaysEncoded(getOptionalValue(setAlwaysEncoded, true));
        return httpArgument;
    }

}
