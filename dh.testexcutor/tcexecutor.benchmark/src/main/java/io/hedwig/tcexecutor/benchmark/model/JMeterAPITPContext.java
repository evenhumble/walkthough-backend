package io.hedwig.tcexecutor.benchmark.model;

import java.util.Map;

import lombok.Data;

/**
 * @@author: patrick
 */
@Data
public class JMeterAPITPContext {

  private ApiDefinition api;
  private int threadNum;
  private int durationTime;
  private String csvPath;
  private String csvParams;
  private int rampupTime = 30;
  private Map<String,String> addtlParams;
}
