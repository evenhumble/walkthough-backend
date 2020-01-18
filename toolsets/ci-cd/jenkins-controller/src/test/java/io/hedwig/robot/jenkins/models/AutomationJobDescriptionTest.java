package io.hedwig.robot.jenkins.models;

import org.junit.Test;

/**
 * Created by patrick on 16/3/29.
 */
public class AutomationJobDescriptionTest {

    @Test
    public void testMergeTemplate() throws Exception {
        AutomationJobDescription jobDescription = new AutomationJobDescription();
        jobDescription.setJobTemplate(JobTemplate.Automation_SVN);
        jobDescription.setSvnPath("https://scm.dooioo.com:8443/svn/test/automation/automation-fangzu");
        jobDescription.setTestType("api");
        jobDescription.setJobName("automation-fangzu-api");
        jobDescription.setDescription("房租自动化测试");
        jobDescription.setUser("110863");
        jobDescription.setViewName("自动化测试");
        String template = jobDescription.mergeTemplate();
        System.out.println(template);
        System.out.println(jobDescription.buildShellCommand());
    }
}