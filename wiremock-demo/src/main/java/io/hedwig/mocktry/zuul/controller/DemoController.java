package io.hedwig.mocktry.zuul.controller;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    @PostMapping("/ping")
    public Map pingWithBody(@RequestBody Map map) {
        return map;
    }
}
