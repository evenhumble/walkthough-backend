package io.hedwig.dh.sprintinternal.tinyioc.aop;

public interface Pointcut {

  ClassFilter getClassFilter();

  MethodMatcher getMethodMatcher();

}
