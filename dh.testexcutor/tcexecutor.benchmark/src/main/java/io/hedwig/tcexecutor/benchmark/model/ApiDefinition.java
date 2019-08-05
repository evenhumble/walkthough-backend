package io.hedwig.tcexecutor.benchmark.model;

import java.util.Map;

import lombok.Data;

/**
 * @@author: patrick
 */
@Data
public class ApiDefinition {
  private String apiName;
  private String url;
  private String httpMethod;
  private String requestBody;
  private Map<String,String> headers;
}
