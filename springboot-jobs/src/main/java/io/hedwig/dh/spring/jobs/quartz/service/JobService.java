package io.hedwig.dh.spring.jobs.quartz.service;


import org.quartz.*;

import java.util.Map;
import java.util.Set;

public class JobService {

    private Scheduler scheduler;

    public void addJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        //add job

        scheduler.scheduleJob(jobDetail,trigger);
    }

    public void addJobs(Map<JobDetail,Set<? extends Trigger>> jobs) throws SchedulerException {
        //add jobs to schedulers
        scheduler.scheduleJobs(jobs,true);
    }
}
