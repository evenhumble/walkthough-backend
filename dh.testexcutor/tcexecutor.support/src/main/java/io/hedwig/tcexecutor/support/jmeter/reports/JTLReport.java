package io.hedwig.tcexecutor.support.jmeter.reports;

import io.hedwig.tcexecutor.support.jmeter.reports.models.*;
import lombok.Getter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;

public class JTLReport {
    @Getter
    private TestResult testResult;
    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    private String isReportable = "TRUE";

    public JTLReport(String filename) {
        try {
            testResult = parse(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createReportableResults(String filename) {
        TestResult reportableSubset = new TestResult();
        reportableSubset.setVersion(testResult.getVersion());
        List<HTTPSample> reportableResults = testResult.getHttpSamples().stream()
                                             .filter(sample -> sample.getLb().endsWith(isReportable))
                                             .collect(Collectors.toList());
        if (reportableResults.size() > 0) {
            reportableSubset.setHttpSamples(reportableResults);
            try {
                marshaller.marshal(reportableSubset, new File(filename));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    private TestResult parse(String filename) throws Exception {
        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(TestResult.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }
        return (TestResult) unmarshaller.unmarshal(new FileInputStream(filename));
    }

}
