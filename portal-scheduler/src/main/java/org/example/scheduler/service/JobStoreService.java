package org.example.scheduler.service;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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
}
