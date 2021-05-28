package org.example.scheduler.service;

import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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
        JobBuilder jobBuilder = new JobDetailImpl().getJobBuilder();
        JobDetail jobDetail = jobBuilder.ofType(StatefulJob.class)
                .withIdentity("test")
                .withDescription("A test Job Stored")
                .storeDurably()
                .build();
        //jobStore.storeJob(jobDetail, true);
        schedulerBean.addJob(jobDetail);
    }

    @Scheduled(every = "10s", identity = "log-alive")
    void logAlive() {
        log.info("I'm alive");
    }
}
