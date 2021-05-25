package org.example.scheduler.service;

import io.quarkus.runtime.Startup;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Startup
@Singleton
@Slf4j
public class SchedulerBean {


    //@Inject
    private Scheduler scheduler;

    @PostConstruct
    public void scheduleJobs() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            printJobsAndTriggers(scheduler);
        } catch (SchedulerException e) {
            log.error("Error while creating scheduler", e);
        }
    }

    private void printJobsAndTriggers(Scheduler scheduler) throws SchedulerException {
        log.info("Quartz Scheduler: {}", scheduler.getSchedulerName());
        for(String group: scheduler.getJobGroupNames()) {
            for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))) {
                log.info("Found job identified by {}", jobKey);
            }
        }
        for(String group: scheduler.getTriggerGroupNames()) {
            for(TriggerKey triggerKey : scheduler.getTriggerKeys(GroupMatcher.<TriggerKey>groupEquals(group))) {
                log.info("Found trigger identified by {}", triggerKey);
            }
        }
    }

    public List<String> getJobGroupNames() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

    public List<String> getTriggerGroupNames() throws SchedulerException {
        return scheduler.getTriggerGroupNames();
    }

    public void addJob(JobDetail detail) throws SchedulerException {
        scheduler.addJob(detail, true);
    }


    @PreDestroy
    public void stopJobs() {
        if (scheduler != null) {
            try {
                scheduler.shutdown(false);
            } catch (SchedulerException e) {
                log.error("Error while closing scheduler", e);
            }
        }
    }
}
