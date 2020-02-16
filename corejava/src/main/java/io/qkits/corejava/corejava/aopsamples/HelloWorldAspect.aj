package io.qkits.corejava.corejava.aopsamples;

/**
 * Created by patrick on 16/6/14.
 */
public aspect HelloWorldAspect {

    pointcut HelloWorldPointCut() : execution(* main(..));
    before(): HelloWorldPointCut(){
        System.out.println("Before hello World");
    }
}
