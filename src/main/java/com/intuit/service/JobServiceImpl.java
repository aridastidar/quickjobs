package com.intuit.service;

import java.util.List;
import java.util.Optional;

import com.intuit.entity.Job;
import com.intuit.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> retrieveJobs() {
        List<Job> employees = jobRepository.findAll();
        return employees;
       }
        
       public Job getJob(Long jobId) {
        Optional<Job> optEmp = jobRepository.findById(jobId);
        return optEmp.get();
       }
        
       public void saveJob(Job job){
        jobRepository.save(job);
       }
        
       public void deleteJob(Long jobId){
        jobRepository.deleteById(jobId);
       }
        



}