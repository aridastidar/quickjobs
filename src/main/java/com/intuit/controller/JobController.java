package com.intuit.controller;

import java.util.List;

import com.intuit.entity.Job;
import com.intuit.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class JobController{

    @Autowired
    private JobService jobService;

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/api/job")
    public List<Job> getJobs(){
        return jobService.retrieveJobs();
    }

    @RequestMapping("/apply")
    public String apply(){
        return "applied";
    }

    @GetMapping("/api/employees/{jobId}")
    public Job getEmployee(@PathVariable(name = "jobId") Long jobId) {
        return jobService.getJob(jobId);
    }

    @PostMapping("/api/employees")
    public void saveEmployee(Job job) {
        jobService.saveJob(job);
        System.out.println("Job Saved Successfully");
    }

    @DeleteMapping("/api/job/{job}")
    public void deleteEmployee(@PathVariable(name = "jobId") Long jobId) {
        jobService.deleteJob(jobId);
        System.out.println("Job Deleted Successfully");
    }
}