package io.hedwig.tcexecutor.support.performance.entity;

import io.hedwig.tcexecutor.support.base.AuditableEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "perf_job")
@Data
public class PerfJob extends AuditableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private Date startTime;
    private Date endTime;
    private String runMachines;  //PerfMachine ids
    private Long perfTestCaseId;
    private String testResultUrl;
    private int jobStatus;
    private String jmxFileLocation;
    private String logFileLocation;
    private String resultLocation;
}
