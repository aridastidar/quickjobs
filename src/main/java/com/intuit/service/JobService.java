package com.intuit.service;

import java.util.List;

import com.intuit.entity.Job;

public interface JobService {
    public List<Job> retrieveJobs();
    public Job getJob(Long id);
    public void saveJob(Job job);
    public void deleteJob(Long jobId);
}