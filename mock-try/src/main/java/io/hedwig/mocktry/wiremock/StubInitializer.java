package io.hedwig.mocktry.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.recording.RecordSpec;
import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
public class StubInitializer {

    @PostConstruct
    public void initStub(){
//        WireMockServerRunner runner = new WireMockServerRunner();
//        runner.run();

       WireMockConfiguration configuration =  WireMockConfiguration.options()
                .enableBrowserProxying(true).port(9091);
        WireMockServer server = new WireMockServer(configuration);
        server.start();
        server.stubFor(get(urlEqualTo("/some/thing"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")));


    }
}
