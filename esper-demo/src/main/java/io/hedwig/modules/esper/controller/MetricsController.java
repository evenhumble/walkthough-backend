package io.hedwig.modules.esper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1. author: patrick
 */
@Controller
public class MetricsController {
    @RequestMapping("/ping")
    public ResponseEntity echoService(){
        return ResponseEntity.ok("pong!");
    }
}
