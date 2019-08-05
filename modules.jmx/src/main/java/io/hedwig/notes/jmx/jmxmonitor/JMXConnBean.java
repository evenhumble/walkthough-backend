package io.hedwig.notes.jmx.jmxmonitor;

import javax.management.remote.JMXConnector;

public class JMXConnBean {

    private JMXConnector connector;
    private String name;
    private String host;
    private String user;
    private String pwd;
    private int port;

    public JMXConnector getConnector() {
        return connector;
    }

    public void setConnector(JMXConnector connector) {
        this.connector = connector;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
