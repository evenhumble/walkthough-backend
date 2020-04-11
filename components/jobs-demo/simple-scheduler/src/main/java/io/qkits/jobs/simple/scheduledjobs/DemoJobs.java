package io.qkits.jobs.simple.scheduledjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoJobs {

    private static final Logger log = LoggerFactory.getLogger(DemoJobs.class);

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime(){
        log.info(Thread.currentThread().getName());
        log.info("this time is now {}", new Date().getTime());
    }

    @Scheduled(cron = "0 20 17 * * ?")
    public void reportCurrentTimeCron(){
        log.info("this is cron scheduled job!");
        log.info("this time is now {}", new Date().getTime());
    }
}
