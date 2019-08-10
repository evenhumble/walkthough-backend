package io.hedwig.tcexecutor.support.api;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HttpApiDefinition {

    private String resourceUrl;
    private String method;
    private String body;
    private Map<String,List<String>> headers;
    private Map<String,String> queryParams;
    private Map<String,String> pathParams;
}
