package io.hedwig.robot.jenkins.controllers;

import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import io.hedwig.robot.jenkins.exceptions.JenkinsJobException;
import io.hedwig.robot.jenkins.models.JenkinsInstance;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;


public class JenkinsJobControllerTest extends JenkinsBaseTest{
    protected JenkinsJobController jobController = new JenkinsJobController(super.jenkinsInstance);

    @org.junit.Test
    public void testGetJobByName() throws Exception {
        JobWithDetails job =(JobWithDetails) this.jobController.getByName("test");
        assertThat("job name checking",job.getDisplayName(),is("test"));
    }

    @org.junit.Test
    public void testGetJobByName_null() throws Exception {
        JobWithDetails job =(JobWithDetails) this.jobController.getByName("not_existing");
        assertNull(job);
    }

    @org.junit.Test
    public void testAllJob() throws Exception {
        Map<String,Job> jobs = this.jobController.getAll();
        assertThat("check jobs is existing",jobs.size(),greaterThan(1));

    }

    @org.junit.Test
    public void testAllJobByViewName() throws Exception {
        Map<String,Job> jobs = this.jobController.getJobsByViewName("All");
        assertThat("check jobs is existing",jobs.size(),greaterThan(1));
    }

    @org.junit.Test
    public void testAllJobByViewName_null() throws Exception {
        Map<String,Job> jobs = this.jobController.getJobsByViewName("All_notExisting");
        assertNull(jobs);
    }

    @org.junit.Test
    public void testAllJobXMLByName() throws Exception {
        String jobXml= this.jobController.getJobXMLByName("test");
        assertThat("check jobs is existing",jobXml.length(),greaterThan(1));
    }

    @org.junit.Test
    public void testCopyJob() throws Exception {
        this.jobController.copy("test","test_manen_1","All");
        assertNotNull(this.jobController.getByName("test_manen_1"));
        this.jobController.delete("test_manen_1");
    }

    @Test
    public void testGetOrCreate_get(){
       Job job= this.jobController.getOrCreate("test",null,"All");
        assertNotNull("check job is not null",job);
    }

    @Test
    public void testGetOrCreate_create(){
        Job job= this.jobController.getOrCreate("test_maven_1","test","All");
        assertNotNull("check job is not null",job);
        this.jobController.delete(job.getName());
    }

    @Test(expected = JenkinsJobException.class)
    public void testDelete_exception(){
        this.jobController.delete("not_existing");
    }

    @Test
    public void testGetJobXML_null(){
        assertNull(this.jobController.getJobXMLByName("not_existing"));
    }

    @Test
    public void getJobXml() throws IOException {
        JenkinsInstance instance = JenkinsInstance.get("http://jenkins.dooioo.org");
//        String xml = instance.getServer().getJobXml("test_automation_template");
        String xml2 = instance.getServer().getJobXml("renzi-ui-test");

//        System.out.println(xml);
        System.out.println(xml2);
    }
}
