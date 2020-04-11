package io.qdriven.plugins.thrift.samplers;

import io.qdriven.plugins.thrift.SimpleThriftClient;
import io.qdriven.plugins.thrift.ThriftClientConstants;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by patrick on 16/8/17.
 */
public abstract class BaseThriftSamplerClient extends AbstractJavaSamplerClient {

    private SimpleThriftClient client = new SimpleThriftClient();

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        client.openConnection(context);
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        invoke(result);
        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
        client.closeConnection();
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = new Arguments();
        defaultParameters.addArgument(ThriftClientConstants.SERVER, "127.0.0.1");
        defaultParameters.addArgument(ThriftClientConstants.PORT, "10001");
        defaultParameters.addArgument(ThriftClientConstants.THRIFT_PROTOCOL, "");
        overrideDefaultArguments(defaultParameters);
        return defaultParameters;
    }

    public abstract SampleResult invoke(SampleResult startedSampleResult);
    public void overrideDefaultArguments(Arguments defaultParameters){}
}
