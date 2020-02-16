package io.qkits.corejava.corejava.aopsamples.cflowsamples;

/**
 * Created by patrick on 16/6/14.
 */
public aspect CflowAspect {

    pointcut barPoint(): execution(* bar(..));
    pointcut barCflow(): execution(* bar(..));
    pointcut fooPoint(): execution(* foo(..));
    pointcut fooInBar(): barCflow() && fooPoint() &&!within(CflowAspect);

    before(): barCfow(){
        System.out.println("Enter:"+thisJoinPoint);
    }
}
