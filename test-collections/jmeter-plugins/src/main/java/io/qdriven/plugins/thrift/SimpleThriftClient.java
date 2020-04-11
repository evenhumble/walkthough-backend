package io.qdriven.plugins.thrift;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by patrick on 16/8/17.
 */
public class SimpleThriftClient {

    private TTransport transport = null;
    private TProtocol protocol = null;

    /**
     * get Thrift connection
     *
     * @param jmeterContext
     */
    public void openConnection(JavaSamplerContext jmeterContext) {
        String host = jmeterContext.getParameter(ThriftClientConstants.SERVER);
        Integer port = jmeterContext.getIntParameter(ThriftClientConstants.PORT);
        this.transport = new TSocket(host, port);
        this.protocol = new TBinaryProtocol(this.transport, true, true);
        try {
            this.transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * close thrift connection
     */
    public void closeConnection() {
        if (this.transport != null) {
            this.transport.close();
        }

    }

}