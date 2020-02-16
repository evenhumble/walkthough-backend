package io.hedwig.robot.jenkins.models;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import io.hedwig.robot.jenkins.exceptions.JenkinsBaseException;
import io.hedwig.robot.jenkins.exceptions.JenkinsCreationException;
import org.apache.commons.lang.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsInstance {

    private JenkinsConfig config = new JenkinsConfig();
    private JenkinsServer server ;

    private JenkinsInstance createServer() {
        if(StringUtils.isEmpty(config.getJenkinsServerUrl()))
            throw new JenkinsBaseException("please at lease setup server url");
        try {
            if (StringUtils.isEmpty(config.getUserName())) { // workaround for anonymous access
                server=new JenkinsServer(new URI(this.config.getJenkinsServerUrl()));
            } else {
                server=new JenkinsServer(new URI(this.config.getJenkinsServerUrl()
                        , this.config.getUserName(), this.config.getPassword()));
            }
        } catch (URISyntaxException e) {
            throw new JenkinsCreationException(e);
        }
        return this;
    }

    public JenkinsHttpClient getJenkinsHttpClient() {
        JenkinsHttpClient client;
        try {
            if (org.springframework.util.StringUtils.isEmpty(this.config.getUserName())) {
                client = new JenkinsHttpClient(new URI(this.config.getJenkinsServerUrl()),
                        this.config.getUserName(),this.config.getPassword());
            } else {
                client = new JenkinsHttpClient(new URI(this.config.getJenkinsServerUrl()));
            }
            return client;
        }catch (Exception e){
            throw new JenkinsBaseException(e);
        }
    }

    /**
     * Jenkins Instance Config entry point
     * @return
     */
    public static JenkinsInstance get(String serverUrl) {
        JenkinsInstance instance =  new JenkinsInstance();
        instance.config.setJenkinsServerUrl(serverUrl);
        instance.createServer();
        return instance;
    }

    public static JenkinsInstance get(String serverUrl,String userName,String password) {
        JenkinsInstance instance =  new JenkinsInstance();
        instance.config.setJenkinsServerUrl(serverUrl);
        instance.config.setPassword(password);
        instance.config.setUserName(userName);
        instance.createServer();
        return instance;    }

    public JenkinsConfig getConfig() {
        return config;
    }

    public void setConfig(JenkinsConfig config) {
        this.config = config;
    }

    public JenkinsServer getServer() {
        return server;
    }

    public static class JenkinsConfig {

        private String jenkinsServerUrl;
        private String userName;
        private String password;
        //todo add more configuration if needed

        public String getJenkinsServerUrl() {
            return jenkinsServerUrl;
        }

        public void setJenkinsServerUrl(String jenkinsServerUrl) {
            this.jenkinsServerUrl = jenkinsServerUrl;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
