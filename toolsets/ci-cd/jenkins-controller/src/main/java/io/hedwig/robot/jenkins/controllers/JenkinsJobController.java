package io.hedwig.robot.jenkins.controllers;

import com.offbytwo.jenkins.model.Job;
import io.hedwig.robot.jenkins.exceptions.JenkinsJobException;
import io.hedwig.robot.jenkins.models.AutomationJobDescription;
import io.hedwig.robot.jenkins.models.JenkinsInstance;

import java.util.Map;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsJobController extends JenkinsBaseController<Job> {

    public static final String JOB_DELETE_PATH = "/job/%s/doDelete";

    public JenkinsJobController(JenkinsInstance jenkinsInstance) {
        super(jenkinsInstance);
    }

    @Override
    public Job getOrCreate(String itemName, String referenceItemName,String parentName) {

        Job job = getByName(itemName);
        if(job!=null) return job;
        this.replicator.copyItem(referenceItemName,itemName,parentName);
        return getByName(itemName);
    }

    /**
     * create job according to the job description
     * @param jobDescription
     * @return
     */
    public Job getOrCreate(AutomationJobDescription jobDescription){
        Job job = getOrCreate(jobDescription.getJobName()
                ,jobDescription.getJenkinsTemplateJob()
                ,jobDescription.getViewName());
        try {
            String configXml = jobDescription.jobConfigXml();
            this.getJenkins().updateJob(jobDescription.getJobName(),configXml,false);
        }catch (Exception e){
            throw new JenkinsJobException("create view failed!",e);
        }
        return job;
    }

    @Override
    public Job copy(String fromName, String toName,String parentName) {
        this.replicator.copyItem(fromName,toName,parentName);
        return getByName(toName);
    }

    @Override
    public void delete(String name) {
        try {
            this.jenkinsInstance.getJenkinsHttpClient().post(String.format(JOB_DELETE_PATH, name));
        } catch (Exception e) {
            throw new JenkinsJobException("delete job failed!", e);
        }
    }

    @Override
    public Map<String, Job> getAll() {
        try {
            return super.getJenkins().getJobs();
        } catch (Exception e) {
            throw new JenkinsJobException(e);
        }
    }

    @Override
    public Job getByName(String name) {
        try {
            return super.getJenkins().getJob(name);
        } catch (Exception e) {
            throw new JenkinsJobException(e);
        }
    }


    public Map <String,Job> getJobsByViewName(String viewName) {
        try {
            return super.getJenkins().getJobs(viewName);
        } catch (Exception e) {
            return null;
        }
    }

    public String getJobXMLByName(String jobName){
        try {
            return super.getJenkins().getJobXml(jobName);
        } catch (Exception e) {
            return null;
        }
    }
}
