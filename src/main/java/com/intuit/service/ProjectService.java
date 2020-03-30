package com.intuit.service;

import java.util.List;
import java.util.Optional;

import com.intuit.entity.Project;


public interface ProjectService {

    Optional<Project> getProjectById(Long id);
    List<Project> getAllProjects();
    void saveProject(Project project);


}
