package io.hedwig.robot.jenkins.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * Created by patrick on 16/3/29.
 */
public class AutomationJobDescription {
    private String svnPath;
    private String user;
    private String testType;
    private String jobName;
    private String description;
    /**
     * JobTemplateName
     */
    private JobTemplate jobTemplate;
    private String viewName;

    public String getSvnPath() {
        return svnPath;
    }

    public void setSvnPath(String svnPath) {
        this.svnPath = svnPath;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String jobConfigXml() {
        String templateXml = this.jobTemplate.templateXml();
        return templateXml.replaceAll("_USER", user).replaceAll("_SVNPATH", svnPath)
                .replaceAll("_TESTTYPE", testType).replaceAll("_DESCRIPTION", description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobTemplate getJobTemplate() {
        return jobTemplate;
    }

    public String getJenkinsTemplateJob(){
        return jobTemplate.templateName().replace(".xml","");
    }
    public void setJobTemplate(JobTemplate jobTemplate) {
        this.jobTemplate = jobTemplate;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String mergeTemplate(){
        Document d = Jsoup.parse(jobConfigXml());
       Elements elements= d.select("description");
        for (Element element : elements) {
            element.text("testing");
        }

        return d.toString();
    }

    public String buildShellCommand(){
        String shellTemplate = "#服务器(虚拟机)IP\n" +
                "SERVER_IP=\"10.8.1.81\"\n" +
                "#服务器登陆用户名\n" +
                "USERNAME=\"tomcat\"\n" +
                "#服务器登陆密码\n" +
                "PASSWORD=\"tomcat\"\n" +
                "#项目tomcat的目录\n" +
                "TOMCAT=\"/home/tomcat/nav8088\"\n" +
                "#jenkins的job构建出的war包的存放位置\n" +
                "WARPATH=\"$WORKSPACE/target\"\n" +
                "#部署服务器的JAVA_HOME变量值\n" +
                "JAVAHOME=\"/usr/java/jdk1.8.0_40\"";
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(shellTemplate));
            properties.setProperty("JAVAHOME","/usr/java/1234");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.toString();
    }
}
