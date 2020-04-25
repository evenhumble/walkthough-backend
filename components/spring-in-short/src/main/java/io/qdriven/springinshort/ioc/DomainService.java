package io.qdriven.springinshort.ioc;

import org.springframework.stereotype.Service;

/**
 * QMETA
 * created at: 2020/4/25
 * created by: patrick
 **/

@Service
public class DomainService {

    public String process1(){
        return "This is Process 1";
    }
}
