package io.hedwig.dh.spring.jobs.scheduledjobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DynamicSpringTask {

    @Autowired
    @Qualifier(value = "taskExecutor")
    private ThreadPoolTaskScheduler executor;

    @PostConstruct
    public void addExistingTasks(){
        executor.initialize();
        System.out.println("ThreadPoolTaskScheduler started....");

        executor.schedule(() -> System.out.println("This is a cron job 5"),new CronTrigger("0 5 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 10"),new CronTrigger("0 10 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 15"),new CronTrigger("0 15 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 20"),new CronTrigger("0 20 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 25"),new CronTrigger("0 25 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 30"),new CronTrigger("0 30 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 35"),new CronTrigger("0 35 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 40"),new CronTrigger("0 40 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 45"),new CronTrigger("0 45 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 50"),new CronTrigger("0 50 * * * *"));

        destroyAllTask();
        reinitializeTasks();

        executor.schedule(() -> System.out.println("This is a reintialized cron job 5"),new CronTrigger("0 5 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintialized cron job 10"),new CronTrigger("0 10 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintialized cron job 15"),new CronTrigger("0 15 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintialized cron job 20"),new CronTrigger("0 20 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 25"),new CronTrigger("0 25 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 30"),new CronTrigger("0 30 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 35"),new CronTrigger("0 35 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 40"),new CronTrigger("0 40 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 45"),new CronTrigger("0 45 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 50"),new CronTrigger("0 50 * * * *"));


    }
//    public static void main(String[] args) {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.initialize();
////        ScheduledFuture future =scheduler.scheduleAtFixedRate(() -> System.out.println("this is scheduler test"),20);
//        scheduler.destroy();
//
//    }

    public void destroyAllTask(){
        System.out.println("executor shutdown");
        this.executor.destroy();
        System.out.println(this.executor.getActiveCount());
    }

    public void reinitializeTasks(){
        System.out.println("reinitialized");
        this.executor.initialize();
        System.out.println("active count after reinitialized :" + this.executor.getActiveCount());
    }
    //getting taskSchedule for Email
}
