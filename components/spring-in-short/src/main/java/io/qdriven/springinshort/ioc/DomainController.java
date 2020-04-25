package io.qdriven.springinshort.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * QMETA
 * created at: 2020/4/25
 * created by: patrick
 **/
@RestController(value = "/domain")
public class DomainController {
    @Autowired
    private DomainService service;
    @GetMapping
    public ResponseEntity<String> getDomainInfo(){
        return ResponseEntity.ok(service.process1());
    }
}
