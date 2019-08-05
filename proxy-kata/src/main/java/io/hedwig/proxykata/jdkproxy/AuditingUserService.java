package io.hedwig.proxykata.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @@author: patrick
 * Proxy Design Patter
 */
public class AuditingUserService implements IUserService {

    private Auditor auditor;
    private UserService userService;

    public String findByName(String name){
        auditor.audit("start-findUserByName",name);
        String result = userService.findByName(name);
        auditor.audit("end-findUserByName",result);
        return  result;
    }

    //not good, because add new audit service, need to write the new class
    // audit is coupled with the service, a audit change, it has to make codes changed
    //in several places
    public static void main(String[] args) {
        Auditor auditor = new Auditor();
        UserService userService = new UserService();
        InvocationHandler handler = new AuditingInvocationHandler(auditor, userService);
        UserService userProxy = (UserService) Proxy.newProxyInstance(
            ClassLoader.getSystemClassLoader(),new Class[]{
                IUserService.class //must be interface
            },handler
        );
       String result = userProxy.findByName("Test");
        System.out.println(result);
    }
}
