package io.hedwig.tcexecutor.support.performance.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class PerfTestCase {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String testCaseName;
    private String loadGeneratorType="JMETER";
    private String requestSample;
    private long jmeterTemplateId;
    private String testScenarioDefinition;  //json object
    private String tcFileLocation;
    private String dataFileLocation;
    private String tcLocation;
    private long apiId;
    private String jmxContent;
}
