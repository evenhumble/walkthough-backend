package io.hedwig.robot.jenkins.controllers;

import io.hedwig.robot.jenkins.models.JenkinsInstance;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsBaseTest {

   protected JenkinsInstance jenkinsInstance= JenkinsInstance.get("http://localhost:8080/");
}
