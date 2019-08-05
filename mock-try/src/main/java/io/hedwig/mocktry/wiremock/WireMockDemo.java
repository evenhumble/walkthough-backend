package io.hedwig.mocktry.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static wiremock.org.hamcrest.MatcherAssert.assertThat;

public class WireMockDemo {

    @Rule
    public  WireMockRule wireMockRule = new WireMockRule(
            options().port(8099).httpsPort(8083)
    );

    @ClassRule
    public static WireMockClassRule wireMockClassRule = new WireMockClassRule(9099);
    @Test
    public void testWireMock_Stub(){
        wireMockRule.stubFor(get(urlEqualTo("/foo")));
        wireMockClassRule.stubFor(get(urlEqualTo("/bar")));
    }

    @Test
    public void demo_wiremock_process(){
        //setup mock server
        wireMockRule.stubFor(get(urlEqualTo("/foo"))
            .willReturn(aResponse().withHeader("Content-Type","application/xml")
            .withBody("Hello World!"))
        );

        //test client, question is that how to setup stub in runtime
    }
}
