package io.qdriven.plugins.thrift.samplers;

import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by patrick on 16/8/17.
 */
public class DummyThriftSampleClient extends BaseThriftSamplerClient {
    @Override
    public SampleResult invoke(SampleResult startedSampleResult) {
        startedSampleResult.setSuccessful(true);
        startedSampleResult.sampleEnd();
        startedSampleResult.setResponseData("this is dummy ThriftSampleClient","utf-8");
        return startedSampleResult;
    }
}
