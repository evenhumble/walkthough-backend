package io.hedwig.tcexecutor.support.jmeter;

public class JMeterScenario {

    private String threadNum;
    private int duration;
    private String jmeterTestPlan;
    private ApiDesc apiDesc;
    private String scenarioName;

    public String getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(String threadNum) {
        this.threadNum = threadNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getJmeterTestPlan() {
        return jmeterTestPlan;
    }

    public void setJmeterTestPlan(String jmeterTestPlan) {
        this.jmeterTestPlan = jmeterTestPlan;
    }

    public ApiDesc getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(ApiDesc apiDesc) {
        this.apiDesc = apiDesc;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }
}
