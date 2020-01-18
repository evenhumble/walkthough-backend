package io.hedwig.robot.jenkins;

import com.google.common.collect.Lists;
import com.offbytwo.jenkins.model.Job;
import io.hedwig.robot.jenkins.controllers.JenkinsJobController;
import io.hedwig.robot.jenkins.controllers.JenkinsViewController;
import io.hedwig.robot.jenkins.models.AutomationJobDescription;
import io.hedwig.robot.jenkins.models.JenkinsInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by patrick on 16/3/28.
 *  todo think about use simple injector lib,
 * but it may bring unnecessary complexity
 */
public class JenkinsRobot {

    private JenkinsJobController jobController;
    private JenkinsViewController viewController;
    private JenkinsInstance jenkinsInstance;

    public static JenkinsRobot getRobot(String severalUrl){
        return new JenkinsRobot(JenkinsInstance.get(severalUrl));
    }

    public static JenkinsRobot getRobot(String severalUrl,String userName,String password){
        return new JenkinsRobot(JenkinsInstance.get(severalUrl,userName,password));
    }

    public JenkinsRobot(JenkinsInstance jenkinsInstance) {
        this.jenkinsInstance = jenkinsInstance;
        jobController=new JenkinsJobController(jenkinsInstance);
        viewController=new JenkinsViewController(jenkinsInstance);
    }

    public void createAutomationJob( AutomationJobDescription jobDescription){

        viewController.getOrCreate(jobDescription.getViewName(),null,null);
        Job job = jobController.getOrCreate(jobDescription);
//        try {
//            job.build();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void copy(String view,String toView){

    }

    public static void main(String[] args) {
        JenkinsRobot robot=JenkinsRobot.getRobot("http://localhost:8080");
//                  .createAutomationJob(jobDescription);

//        Collection<View> views =  robot.viewController.getAll().values();

//        robot.viewController.getOrCreate(viewName,null,null);
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//
//            robot.jobController.copy(entry.getValue(),viewName+"-"+entry.getValue(),viewName);
//            System.out.println("complete copy "+entry.getValue());
//        }

//        for (View view : views) {
//            Map<String,Job> jobs =  robot.jobController.getJobsByViewName(view.getName());
//        Map<String,Job> jobs = robot.jobController.getAll();
//            for (Map.Entry<String, Job> entry : jobs.entrySet()) {
//                String xml = robot.jobController.getJobXMLByName(entry.getKey());
//                for (String s : folders) {
//                    if(xml.contains(s)) {
//                        System.out.println(String.format("map.put(\"%s\",\"%s\")",s,entry.getKey()));
//                        break;
//                    }
//                }
//
//            }
//        }


    }
}
