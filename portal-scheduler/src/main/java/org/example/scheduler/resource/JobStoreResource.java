package org.example.scheduler.resource;


import org.example.scheduler.service.JobStoreService;
import org.quartz.JobPersistenceException;
import org.quartz.SchedulerException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/jobs")
public class JobStoreResource {

    @Inject
    JobStoreService jobStoreService;

    @GET
    public Response getJobs() throws SchedulerException {
        return Response.ok(jobStoreService.getAllJobs()).build();
    }

    @POST
    public void createJob() throws SchedulerException {
        jobStoreService.createNewJob();
    }
}
