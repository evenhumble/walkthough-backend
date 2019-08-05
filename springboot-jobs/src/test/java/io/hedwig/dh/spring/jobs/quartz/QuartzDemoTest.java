package io.hedwig.dh.spring.jobs.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzDemoTest {

    Scheduler scheduler;


    public  void testSchedulerJob() throws SchedulerException {
        scheduler=StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(SimpleQuartzJob.class)
                .withIdentity("job1","group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(4)
                .repeatForever()).build();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

    public static void main(String[] args) throws SchedulerException {
        QuartzDemoTest demo = new QuartzDemoTest();
        demo.testSchedulerJob();
    }
}