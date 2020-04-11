package io.qdriven.plugins.redis.jmeterplugin;


import io.qdriven.plugins.redis.commands.RedisCommandExecutor;
import io.qdriven.plugins.redis.commands.RedisExecutor;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Created by patrick on 16/8/12.
 */
public class SimpleRedisSamplerClient implements JavaSamplerClient {

    private Jedis client;
    private Pipeline pl;
    private boolean isPipelined = false;
    private int pipedNumber = 1;
    private int batchCnt = 10;
    private String[] keys;
    private String[] members;

    @Override
    public void setupTest(JavaSamplerContext context) {
        String host = context.getParameter("Host");
        int port = context.getIntParameter("Port");
        batchCnt = context.getIntParameter("batchCnt",batchCnt);
        int dataSize = context.getIntParameter("dataSize");
        isPipelined = context.getParameter("isPipelined","Y").matches("Y|y");
        if (isPipelined) {
            pipedNumber = context.getIntParameter("pipedNumber");
        }
        this.client=new Jedis(host,port);
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        RedisExecutor executor = new RedisCommandExecutor();
        SampleResult result = new SampleResult();
        result.sampleStart();
        Object response;
        try{
            response = executor.execute(javaSamplerContext,this.client);
            result.setSuccessful(true);
        }catch (Exception e){
            response = "error!"+e;
            result.setSuccessful(false);
        }
        result.sampleEnd();
        result.setResponseData(response.toString(),"utf-8");
        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        client.close();
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("Host", "127.0.0.1");
        params.addArgument("Port", "6379");
        params.addArgument("isPipelined", "Y");
        params.addArgument("pipedNumber", "10");
        params.addArgument("batchCnt", "10");
        params.addArgument("dataSize", "10");
        return params;
    }
}
