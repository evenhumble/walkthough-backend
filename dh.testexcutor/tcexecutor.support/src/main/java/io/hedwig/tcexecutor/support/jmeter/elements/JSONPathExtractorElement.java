package io.hedwig.tcexecutor.support.jmeter.elements;


import com.google.common.base.Preconditions;

import lombok.Builder;
import org.apache.jmeter.extractor.json.jsonpath.JSONPostProcessor;
import org.apache.jmeter.testelement.TestElement;

@Builder
public class JSONPathExtractorElement extends JMeterStepImpl<JSONPathExtractorElement> {

    private String name;
    private String toVariable;
    private String jsonPath;
    private String defaultValue;
    private String fromVariable;
    private String subject;
    //todo: understand the new json extractor
    public TestElement getTestElement() {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(jsonPath);
        Preconditions.checkNotNull(toVariable);
        Preconditions.checkNotNull(defaultValue);
        Preconditions.checkNotNull(fromVariable);
        JSONPostProcessor jsonPathExtractor = new JSONPostProcessor();
        jsonPathExtractor.setProperty(TestElement.GUI_CLASS, JSONPostProcessor.class.getName());
        jsonPathExtractor.setProperty(TestElement.TEST_CLASS, JSONPostProcessor.class.getName());
        jsonPathExtractor.setName(name);
        jsonPathExtractor.setEnabled(true);
        jsonPathExtractor.setDefaultValues(defaultValue);
        jsonPathExtractor.setJsonPathExpressions(jsonPath);
        jsonPathExtractor.setScopeVariable(toVariable);

        return jsonPathExtractor;
    }

}
