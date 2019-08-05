package io.hedwig.modules.tf.testng;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;

public class TestListenerDemo implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getMethod().getDescription());
        Annotation annotation = result.getMethod().getConstructorOrMethod()
            .getMethod().getAnnotation(Test.class);

        System.out.println(((Test) annotation).testName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
