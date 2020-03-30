package com.intuit.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.intuit.entity.Project;
import com.intuit.entity.Seller;
import com.intuit.repository.ProjectRepository;
import com.intuit.repository.SellerRepository;
import com.intuit.util.QuickjobsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    ProjectRepository projectRepository;
    SellerRepository sellerRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, SellerRepository sellerRepository) {
        this.projectRepository = projectRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void saveProject(Project project) {
        Optional<Seller> findSeller = sellerRepository.findById(project.getSellerId());
        if (!findSeller.isPresent()) {
            throw new QuickjobsException("Please POST the seller entry in /sellers endpoint before adding the project.");
        } else{
            if(project.getLastApplyDate().after(new Date())){
                projectRepository.saveAndFlush(project);
            }else{
                throw new QuickjobsException("Please enter the deadline of the Project greater than today's date.");
            }
        }
    }

  }
