package io.hedwig.proxykata.jdkproxy;

/**
 * @@author: patrick
 */
public class Auditor {

    public void audit(String serviceName,String data){
        System.out.println(String.format("Service %s, Data %s",serviceName, data));
    }
}
