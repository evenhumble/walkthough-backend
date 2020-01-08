package io.hedwig.robot.jenkins.controllers;

import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.View;
import io.hedwig.robot.jenkins.exceptions.JenkinsViewException;
import io.hedwig.robot.jenkins.models.JenkinsInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsViewController extends JenkinsBaseController<View> {
    private JenkinsJobController jobController;

    public JenkinsViewController(JenkinsInstance jenkinsInstance) {
        super(jenkinsInstance);
        this.jobController = new JenkinsJobController(jenkinsInstance);
    }

    @Override
    public View getOrCreate(String itemName, String referenceItemName, String parentName) {
        View view = getByName(itemName);
        if (view != null) return view;
        createView(itemName);
        return getByName(itemName);

    }

    /**
     * create a new View
     *
     * @param itemName
     */
    private void createView(String itemName) {
        try {
            JenkinsHttpClient client = this.jenkinsInstance.getJenkinsHttpClient();
            Map<String, String> formData = new HashMap<>();
            formData.put("name", itemName);
            formData.put("mode", "hudson.model.ListView");
            formData.put("json", String.format("{\"name\": \"%s\", \"mode\": \"hudson.model.ListView\"}", itemName));
            formData.put("Submit", "OK");
            client.post_form(CREATE_VIEW_PATH, formData, false);
        } catch (Exception e) {
            throw new JenkinsViewException(itemName + " view is not created!");
        }
    }

    /**
     * @param fromName          : from item could be none, if so, create a maven item or create based on configuration
     * @param toName:           the new Jenkins View with jobs
     * @param parentNameOrNull: for view no need to add view
     * @return: copied view
     */
    @Override
    public View copy(String fromName, String toName, String parentNameOrNull) {
        View fromView = getByName(fromName);
        if (fromView == null) throw new JenkinsViewException(fromName + "view is not found,can't copy from it!");
        View toView = getOrCreate(toName, fromName, parentNameOrNull);
        Map<String, Job> viewJobs = jobController.getJobsByViewName(fromName);
        for (Map.Entry<String, Job> entry : viewJobs.entrySet()) {
            jobController.copy(entry.getKey(), toName + "_" + entry.getKey(), toView.getName());
        }

        return getByName(toName);
    }

    @Override
    public void delete(String name) {
        try {
            View view = getByName(name);
            if(view==null) return;
            Map<String, String> formData = new HashMap<>();
            formData.put("json", "{}");
            formData.put("Submit", "Yes");
            this.jenkinsInstance.getJenkinsHttpClient().post_form(String.format(DELETE_VIEW_PATH, name), formData, false);
            //delete jobs
            for (Job job : view.getJobs()) {
                this.jobController.delete(job.getName());
            }
        } catch (IOException e) {
            throw new JenkinsViewException(name + "is not deleted successfully!", e);
        }
    }

    @Override
    public Map getAll() {
        try {
            return super.getJenkins().getViews();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public View getByName(String name) {
        try {
            return this.getJenkins().getView(name);
        } catch (Exception e) {
            return null;
        }
    }

    public static final String CREATE_VIEW_PATH = "/createView?";
    public static final String DELETE_VIEW_PATH = "/view/%s/doDelete?";


}
