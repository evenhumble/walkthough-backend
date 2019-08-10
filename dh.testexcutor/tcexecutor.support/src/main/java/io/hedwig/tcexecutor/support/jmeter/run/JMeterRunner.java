package io.hedwig.tcexecutor.support.jmeter.run;

import io.hedwig.tcexecutor.support.jmeter.elements.JMeterStep;
import io.hedwig.tcexecutor.support.jmeter.reports.JTLReport;
import io.hedwig.tcexecutor.support.jmeter.reports.SummaryReport;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Slf4j
public class JMeterRunner extends Observable {
    @Getter
    private final String testPlanName;
    @Getter
    private final String testPlanFileName;
    public CookieManager cookieManager;
    @Setter
    protected String jmeterResourceDir;
    protected File jmeterProperties;
    protected StandardJMeterEngine jmeter;
    protected ListedHashTree testPlanTree;
    private TestPlan testPlan;
    private SummaryReport summaryResults;
    private ArrayList<JMeterStep> steps;

    public JMeterRunner(String testPlanName) {
        this(testPlanName, JMeterRunner.class.getClassLoader().getResource("jmeter").getPath());
    }

    public JMeterRunner(String testPlanName, String jmeterResourceDir) {
        this.jmeterResourceDir = jmeterResourceDir;
        jmeter = new StandardJMeterEngine();
        JMeterUtils.setJMeterHome(jmeterResourceDir);
        readProperties();
        JMeterUtils.initLogging();
        JMeterUtils.initLocale();
        testPlanTree = new ListedHashTree();
        this.testPlanName = testPlanName;
        this.testPlanFileName = testPlanName.toLowerCase().replaceAll("\\s+", "-");
        createTestPlan();
    }

    private void createTestPlan() {
        log.info("Creating test plan:" + testPlanName);

        testPlan = getTestPlan();
        testPlanTree.add(testPlan);
    }

    public void addStep(JMeterStep step) {
        if (steps == null) steps = new ArrayList<>();
        steps.add(step);
    }

    public TestPlan getTestPlan() {
        TestPlan testPlan = new TestPlan();

        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setProperty(TestElement.ENABLED, true);
        testPlan.setFunctionalMode(false);
        testPlan.setSerialized(true);
        Arguments arguments = new Arguments();
        arguments.setProperty(TestElement.GUI_CLASS, "org.apache.jmeter.protocol.http.gui.HTTPArgumentsPanel");
        arguments.setProperty(TestElement.TEST_CLASS, "org.apache.jmeter.config.Arguments");
        arguments.setName("User Defined Variables");
        arguments.setProperty(TestElement.ENABLED, true);
        testPlan.setUserDefinedVariables(arguments);
        testPlan.setTestPlanClasspath("");
        return testPlan;
    }

    private void addTestSteps() {

        for (JMeterStep step : steps) {
            step.setOutputFilePath(String.format("%s/%s-", JMeterRunner.getOutputDirectory(), testPlanFileName));
            HashTree childHashTree = testPlanTree.add(testPlan, (Object) step.getTestElement());
            if (step.getSteps().size() > 0) addChildTestElements(childHashTree, step);
        }
    }

    // recursively create the child nodes
    private void addChildTestElements(HashTree hashTree, JMeterStep currentStep) {
        log.info(String.format("Adding child steps for %s", currentStep.getTestElement().getName()));
        for (JMeterStep childStep : currentStep.getSteps()) {
            childStep.setOutputFilePath(String.format("%s/%s-", JMeterRunner.getOutputDirectory(), testPlanFileName));
            HashTree childHashTree = hashTree.add((Object) childStep.getTestElement());
            if (childStep.getSteps().size() > 0) addChildTestElements(childHashTree, childStep);
        }
    }

    private void addJTLResultsCollector() {
        ResultCollector resultCollector = new ResultCollector();
        resultCollector.setProperty(TestElement.GUI_CLASS, "org.apache.jmeter.visualizers.ViewResultsFullVisualizer");
        resultCollector.setProperty(TestElement.TEST_CLASS, "org.apache.jmeter.reporters.ResultCollector");
        resultCollector.setProperty(TestElement.NAME, "View Results Tree");
        resultCollector.setProperty(TestElement.ENABLED, true);
        resultCollector.setProperty("ResultCollector.error_logging", false);
        SampleSaveConfiguration sampleSaveConfiguration = new SampleSaveConfiguration();
        sampleSaveConfiguration.setAsXml(true);
        sampleSaveConfiguration.setFieldNames(false);
        sampleSaveConfiguration.setResponseData(true);
        sampleSaveConfiguration.setResponseHeaders(true);
        sampleSaveConfiguration.setFileName(true);
        sampleSaveConfiguration.setSampleCount(true);
        sampleSaveConfiguration.setEncoding(true);
        sampleSaveConfiguration.setRequestHeaders(true);
        sampleSaveConfiguration.setMessage(true);
        sampleSaveConfiguration.setSamplerData(true);
        sampleSaveConfiguration.setHostname(true);
        sampleSaveConfiguration.setFieldNames(true);
        resultCollector.setSaveConfig(sampleSaveConfiguration);
        resultCollector.setProperty("filename", getFileName("jtl"));
        testPlanTree.add(testPlan, resultCollector);
    }


    private void addSummaryReport() {
        ResultCollector collector = new ResultCollector();
        collector.setProperty(TestElement.GUI_CLASS, "org.apache.jmeter.visualizers.SummaryReport");
        collector.setProperty(TestElement.TEST_CLASS, "org.apache.jmeter.visualizers.ResultCollector");
        collector.setProperty(TestElement.NAME, "summary");
        collector.setProperty(TestElement.ENABLED, true);
        collector.setSaveConfig(getSampleSaveConfiguration());
        collector.setFilename(getFileName("summary", "xml"));
        collector.setProperty("interval_grouping", 1000);
        collector.setProperty("graph_aggregated", false);
        collector.setProperty("include_checkbox_state", false);
        collector.setProperty("exclude_checkbox_state", false);
        testPlanTree.add(testPlan, collector);
    }

    // user the isReportable flag to generate a second jtl
    // that only has a subset of the test results
    private void createReportableJtl() {
        new JTLReport(getFileName("jtl")).createReportableResults(getFileName("reportable", "jtl"));
    }

    public SummaryReport getSummaryResults() {
        if (summaryResults == null) summaryResults = new SummaryReport(getFileName("summary", "xml"));
        return summaryResults;
    }

    public void createJMX() {
        try {
            SaveService.saveTree(testPlanTree, new FileOutputStream(getFileName("jmx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readProperties() {
        jmeterProperties = new File(jmeterResourceDir + "/bin/jmeter.properties");
        if (!jmeterProperties.exists()) {
            throw new RuntimeException("Unable to locate file: " + jmeterProperties.getAbsolutePath());
        }
        JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
    }

    public static String getOutputDirectory() {
        String dir = ClassLoader.getSystemClassLoader().getSystemResource("").getPath() + "../jmeter";
        File file = new File(dir);
        if (!file.exists()) file.mkdir();
        return dir;
    }

    public void cleanOutputDirectory() {
        File outputDirectory = new File(getOutputDirectory());
        for (File file : outputDirectory.listFiles()) {
            file.delete();
        }
    }

    public CookieManager getCookieManager() {
        if (cookieManager == null) {
            cookieManager = new CookieManager();
            cookieManager.setProperty(TestElement.GUI_CLASS, "org.apache.jmeter.protocol.http.gui.CookiePanel");
            cookieManager.setProperty(TestElement.TEST_CLASS, "org.apache.jmeter.protocol.http.control.CookieManager");
            cookieManager.setProperty(TestElement.ENABLED, true);
            cookieManager.setName("HTTP Cookie Manager");
            cookieManager.setClearEachIteration(true);
            cookieManager.setCookiePolicy("compatibility");
            cookieManager.setImplementation("org.apache.jmeter.protocol.http.control.HC4CookieHandler");
            testPlanTree.add(testPlan, cookieManager);
        }
        return cookieManager;
    }

    public String getFileName(String name, String extension) {
        return String.format("%s/%s-%s.%s", JMeterRunner.getOutputDirectory(), testPlanFileName, name, extension);
    }

    public String getFileName(String extension) {
        return String.format("%s/%s.%s", JMeterRunner.getOutputDirectory(), testPlanFileName, extension);
    }

    private SampleSaveConfiguration getSampleSaveConfiguration() {
        SampleSaveConfiguration ssc = new SampleSaveConfiguration();
        ssc.setTime(true);
        ssc.setLatency(true);
        ssc.setTimestamp(true);
        ssc.setSuccess(true);
        ssc.setLabel(true);
        ssc.setCode(true);
        ssc.setMessage(false);
        ssc.setThreadName(true);
        ssc.setDataType(false);
        ssc.setEncoding(false);
        ssc.setAssertions(true);
        ssc.setSubresults(false);
        ssc.setResponseData(false);
        ssc.setSamplerData(false);
        ssc.setAsXml(true);
        ssc.setFieldNames(true);
        ssc.setResponseHeaders(false);
        ssc.setAssertionResultsFailureMessage(false);
        ssc.setBytes(true);
        ssc.setHostname(true);
        ssc.setThreadCounts(true);
        ssc.setSampleCount(true);
        return ssc;
    }

    public JMeterRunner run() {
        cleanupFiles();
        getCookieManager();
        addTestSteps();
        addJTLResultsCollector();
        addSummaryReport();
        jmeter.configure(testPlanTree);
        createJMX();
        updateObserversStart();
        jmeter.run();
        updateObserversStop();
        createReportableJtl();
        jmeter.exit();
        return this;
    }

    private void cleanupFiles() {
        List<File> files = new ArrayList<>();
        files.add(new File(getFileName("jtl")));
        files.add(new File(getFileName("reportable", "jtl")));
        files.add(new File(getFileName("summary", "xml")));
        for (File file : files) file.delete();
    }

    private void updateObserversStart() {
        setChanged();
        JMeterUpdate update = new JMeterUpdate();
        update.setTestPlanName(testPlanName);
        update.setState(JMeterStatus.STARTED);
        notifyObservers(update);
    }

    private void updateObserversStop() {
        setChanged();
        JMeterUpdate update = new JMeterUpdate();
        update.setTestPlanName(testPlanName);
        update.setState(JMeterStatus.STOPPED);
        notifyObservers(update);
    }

}
