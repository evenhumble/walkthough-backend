package io.hedwig.tcexecutor.support.jmeter.run;

import io.hedwig.tcexecutor.support.jmeter.elements.HTTPSamplerElement;

public class HttpSteps {
    public static HTTPSamplerElement baiduSearch(){
       return HTTPSamplerElement.builder()
                .domain("www.baidu.com")
                .protocol("http")
                .useKeepAlive(true)
                .followRedirects(true)
                .doMultiPartPost(true)
                .method("GET")
                .build();
    }
}
