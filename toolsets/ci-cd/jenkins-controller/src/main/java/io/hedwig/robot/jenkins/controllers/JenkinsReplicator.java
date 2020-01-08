package io.hedwig.robot.jenkins.controllers;

import com.offbytwo.jenkins.client.JenkinsHttpClient;
import io.hedwig.robot.jenkins.exceptions.JenkinsBaseException;
import io.hedwig.robot.jenkins.models.JenkinsInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by patrick on 16/3/29.
 * Replicate different items
 */
public class JenkinsReplicator {
    private final String COPY_FROM_VIEW_URL = "/view/%s/createItem?";
    private final String DEFAULT_VIEW = "All";
    private JenkinsInstance instance;
    private JenkinsHttpClient client;

    public JenkinsReplicator(JenkinsInstance instance) {
        this.instance = instance;
        this.client = instance.getJenkinsHttpClient();
    }

    /**
     * @param fromName: copied from issue
     * @param toName:   new Name
     * @param parentName: to View name
     */
    public void copyItem(String fromName, String toName, String parentName) {
        Map<String, String> formData = new HashMap<>();
        formData.put("name", toName);
        formData.put("mode", "copy");
        formData.put("from", fromName);
        formData.put("json", String.format("{\"name\": \"%s\", \"mode\": \"copy\", \"from\": \"%s\"}", toName, fromName));
        formData.put("Submit", "OK");
        try {
            this.client.post_form(String.format(COPY_FROM_VIEW_URL, parentName), formData, false);
        } catch (IOException e) {
            throw new JenkinsBaseException("copy item "+ fromName+ "to " + toName+" in view " + parentName + " failed", e);
        }
    }

    /**
     * copy job to All view,only copy job
     *
     * @param fromName
     * @param toName
     */
    public void copyItemToAll(String fromName, String toName) {
        copyItem(fromName, toName, DEFAULT_VIEW);
    }


}
