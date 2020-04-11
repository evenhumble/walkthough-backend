package io.qkits.walkthough.netty.adapter.api.serv.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
public class ServiceInstance {
    private final static String MAIN_PORT_KEY = "main";
    private final static String DAEMON_PORT_KEY = "back";
    private final static String PROTOCOL_KEY = "protocol";
    private final static String SOA_VERSION_KEY = "soaVersion";
    private String name;
    private String cluster;
    private String ip;
    private Map<String, Integer> port = new HashMap<>();
    private Map<String, String> meta = new HashMap<>();
    private ServiceRuntime runtime;

    public ServiceInstance(String name, String cluster, String ip, int mainPort, int daemonPort, String protocol, String soaVersion, ServiceRuntime runtime) {
        setName(name);
        setCluster(cluster);
        setIp(ip);
        setMainPort(mainPort);
        setDaemonPort(daemonPort);
        setProtocol(protocol);
        setSoaVersion(soaVersion);
        setRuntime(runtime);
    }

    @JsonIgnore
    public void setMainPort(int mainPort) {
        port.put(MAIN_PORT_KEY, mainPort);
    }

    @JsonIgnore
    public Integer getMainPort() {
        return port.get(MAIN_PORT_KEY);
    }

    @JsonIgnore
    public void setDaemonPort(int daemonPort) {
        port.put(DAEMON_PORT_KEY, daemonPort);
    }

    @JsonIgnore
    public Integer getDaemonPort() {
        return port.get(DAEMON_PORT_KEY);
    }

    @JsonIgnore
    public void setProtocol(String protocol) {
        meta.put(PROTOCOL_KEY, protocol);
    }

    @JsonIgnore
    public String getProtocol() {
        return meta.get(PROTOCOL_KEY);
    }

    @JsonIgnore
    public void setSoaVersion(String soaVersion) {
        meta.put(SOA_VERSION_KEY, soaVersion);
    }

    @JsonIgnore
    public String getSoaVersion() {
        return meta.get(SOA_VERSION_KEY);
    }

    @JsonIgnore
    public void setRuntime(ServiceRuntime runtime) {
        this.runtime = runtime;
    }

    @JsonIgnore
    public ServiceRuntime getRuntime() {
        return runtime;
    }

    @JsonIgnore
    public String getIdentifier() {
        return String.format("%s_%s", getIp(), getMainPort());
    }
}
