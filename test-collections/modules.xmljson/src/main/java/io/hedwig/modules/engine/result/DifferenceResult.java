package io.hedwig.modules.engine.result;

public class DifferenceResult {
    private String node;
    private String sourceValue;
    private String testValue;
    private String missingNode;
    private String extraNode;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getMissingNode() {
        return missingNode;
    }

    public void setMissingNode(String missingNode) {
        this.missingNode = missingNode;
    }

    public String getExtraNode() {
        return extraNode;
    }

    public void setExtraNode(String extraNode) {
        this.extraNode = extraNode;
    }
}
