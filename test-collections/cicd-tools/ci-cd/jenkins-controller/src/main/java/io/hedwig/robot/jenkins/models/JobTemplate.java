package io.hedwig.robot.jenkins.models;

import io.hedwig.robot.jenkins.exceptions.JenkinsBaseException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by patrick on 16/3/29.
 */
public enum JobTemplate {
    Automation_SVN,Automation_GIT,SPRINGBOOT_JAR_GIT, WAR_SVN,SPRINGBOOT_JAR_SVN, WAR_GIT;

    private static Map<JobTemplate,String> templateMapping = new ConcurrentHashMap<>();

    static {
        templateMapping.put(Automation_SVN, "automation_svn_template.xml");
        templateMapping.put(Automation_GIT,"automation_git_template.xml");
        templateMapping.put(SPRINGBOOT_JAR_GIT,"springboot_jar_git_template.xml");
        templateMapping.put(SPRINGBOOT_JAR_SVN,"sprintboot_jar_svn_template.xml");
        templateMapping.put(WAR_GIT,"war_git_template.xml");
        templateMapping.put(WAR_SVN,"war_svn_template.xml");
    }

    public String templateXml() {
        File file = new File(this.getClass().getClassLoader()
                .getResource(templateMapping.get(this)).getPath());
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            throw new JenkinsBaseException("can't find the job template file for "+ this.name(),e);
        }
    }

    public String templateName(){
        return templateMapping.get(this);
    }

}
