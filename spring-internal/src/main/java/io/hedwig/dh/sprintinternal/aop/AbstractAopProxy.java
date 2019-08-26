package io.hedwig.dh.sprintinternal.aop;

public abstract class AbstractAopProxy implements AopProxy {

  protected AdvisedSupport advised;

  public AbstractAopProxy(AdvisedSupport advisedSupport) {
    this.advised = advisedSupport;
  }

}
