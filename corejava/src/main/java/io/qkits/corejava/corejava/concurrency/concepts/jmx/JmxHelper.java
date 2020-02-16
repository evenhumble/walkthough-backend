package io.qkits.corejava.corejava.concurrency.concepts.jmx;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.List;
import java.util.Set;

/**
 * Created by patrick on 16/6/6.
 */
public class JmxHelper {

    public static void main(String[] args) throws Exception {
        if(args.length!=1){
            System.out.println("Usage: jmxHelper: [Host:port]");
        }

        List<String> jmxResults= Lists.newArrayList();
        JMXServiceURL url = new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://%s/jmxrmi",args[0]));
        JMXConnector jmxConnector = JMXConnectorFactory.connect(url, null);
        try {

            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
            Set<ObjectName> objectNameSet = Sets.newTreeSet(mBeanServerConnection.queryNames(null, null));
            for (ObjectName objectName : objectNameSet) {
                MBeanInfo mBeanInfo = mBeanServerConnection.getMBeanInfo(objectName);
                MBeanAttributeInfo[] mBeanAttributeInfos = mBeanInfo.getAttributes();
                for (MBeanAttributeInfo mBeanAttributeInfo : mBeanAttributeInfos) {
                    Object oo = mBeanServerConnection.getAttribute(objectName, mBeanAttributeInfo.getName());
                    if (oo instanceof Boolean || oo instanceof Long){
                        jmxResults.add(String.format("%s %s$%s", objectName, mBeanAttributeInfo.getName(), oo));
                    }else{
                        CompositeDataSupport support = (CompositeDataSupport) oo;
                        jmxResults.add(String.format("%s %s$(committed=%s; init=%s; max=%s; used=%s)", objectName,
                                mBeanAttributeInfo.getName(),
                                support.get("committed"), support.get("init"), support.get("max"), support.get("used")));
                    }
                }
            }
        }finally {
            jmxConnector.close();
        }

    }
}
