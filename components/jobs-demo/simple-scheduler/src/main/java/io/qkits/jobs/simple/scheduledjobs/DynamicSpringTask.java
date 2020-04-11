package io.qkits.jobs.simple.scheduledjobs;

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

    public void addNewTask(CronTrigger trigger){
        executor.schedule(() -> System.out.println("add new tasks ...."),trigger);
    }

    public void stopTask(String name){
        //todo: re-iterator all the tasks, then remove one;
        //this is only scheduler, task, and running time, should be managed by other component
    }

    @PostConstruct
    public void addExistingTasks() {
        executor.initialize();
        System.out.println("ThreadPoolTaskScheduler started....");

        executor
                .schedule(() -> System.out.println("This is a cron job 5"), new CronTrigger("0 5 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 10"),
                new CronTrigger("0 10 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 15"),
                new CronTrigger("0 15 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 20"),
                new CronTrigger("0 20 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 25"),
                new CronTrigger("0 25 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 30"),
                new CronTrigger("0 30 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 35"),
                new CronTrigger("0 35 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 40"),
                new CronTrigger("0 40 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 45"),
                new CronTrigger("0 45 * * * *"));
        executor.schedule(() -> System.out.println("This is a cron job 50"),
                new CronTrigger("0 50 * * * *"));

        destroyAllTask();
        reinitializeTasks();

        executor.schedule(() -> System.out.println("This is a reintialized cron job 5"),
                new CronTrigger("0 5 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintialized cron job 10"),
                new CronTrigger("0 10 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintialized cron job 15"),
                new CronTrigger("0 15 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintialized cron job 20"),
                new CronTrigger("0 20 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 25"),
                new CronTrigger("0 25 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 30"),
                new CronTrigger("0 30 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 35"),
                new CronTrigger("0 35 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 40"),
                new CronTrigger("0 40 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 45"),
                new CronTrigger("0 45 * * * *"));
        executor.schedule(() -> System.out.println("This is a reintializedcron job 50"),
                new CronTrigger("0 50 * * * *"));


    }

    public void destroyAllTask() {
        System.out.println("executor shutdown");
        this.executor.destroy();
        System.out.println(this.executor.getActiveCount());
    }

    public void reinitializeTasks() {
        System.out.println("reinitialized");
        this.executor.initialize();
        System.out.println("active count after reinitialized :" + this.executor.getActiveCount());
    }
}
