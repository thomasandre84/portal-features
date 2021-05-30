package org.example.scheduler.service;

import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.microprofile.opentracing.Traced;
import org.example.scheduler.job.SimpleJob;
import org.quartz.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Slf4j
@ApplicationScoped
public class JobStoreService {

    @Inject
    SchedulerBean schedulerBean;

    public Map<String, List<String>> getAllJobs() throws SchedulerException {
        List<String> groups = schedulerBean.getJobGroupNames();
        List<String> triggers = schedulerBean.getTriggerGroupNames();
        return Map.of("Groups", groups, "Triggers", triggers);
    }

    public void createNewJob() throws SchedulerException {
        //JobStore jobStore = new JobStoreCMT();
        /*JobBuilder jobBuilder = new JobDetailImpl().getJobBuilder();
        JobDetail jobDetail = jobBuilder.ofType(StatefulJob.class)
                .withIdentity("test")
                .withDescription("A test Job Stored")
                .storeDurably()
                .build();*/

        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("myJob", "group1")
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", "3.141f")
                .storeDurably()
                .build();
        //jobStore.storeJob(jobDetail, true);
        schedulerBean.addJob(job);

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
                .forJob("myJob", "group1")
                .build();


    }

    @Scheduled(every = "10s", identity = "log-alive")
    @Traced
    void logAlive() {
        log.info("I'm alive");
    }
}
