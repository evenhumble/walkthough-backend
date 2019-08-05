package io.hedwig.dh.spring.jobs.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimplePrintJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("start Simple Print Job");
        System.out.println(jobExecutionContext.getFireTime());
        System.out.println(jobExecutionContext.getNextFireTime());
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap());
        System.out.println(jobExecutionContext.getJobRunTime());
        System.out.println(jobExecutionContext.getResult());
    }
}
