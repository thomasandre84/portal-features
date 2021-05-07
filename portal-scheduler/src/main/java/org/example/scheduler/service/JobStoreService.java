package org.example.scheduler.service;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobPersistenceException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.jdbcjobstore.JobStoreCMT;
import org.quartz.spi.JobStore;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JobStoreService {

    public boolean storeSchedule() throws JobPersistenceException {
        JobStore jobStore = new JobStoreCMT();

        JobBuilder jobBuilder = new JobDetailImpl().getJobBuilder();
        JobDataMap jobDataMap = new JobDataMap();
        JobDetail jobDetail = jobBuilder.setJobData(jobDataMap).build();
        jobStore.storeJob(jobDetail, true);
        return true;
    }
}
