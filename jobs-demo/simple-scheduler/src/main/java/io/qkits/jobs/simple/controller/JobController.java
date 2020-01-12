package io.qkits.jobs.simple.controller;

import io.qkits.jobs.simple.scheduledjobs.DynamicSpringTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/jobs")
public class JobController {
    @Autowired
    private DynamicSpringTask taskService;

    @PostMapping("/jobs")
    public ResponseEntity setupJob(@RequestBody  Map<String,String> generalParams){
        CronTrigger trigger = new CronTrigger(generalParams.get("cron"));
        taskService.addNewTask(trigger);
        return ResponseEntity.ok("Job is added");
    }
}
