package com.north47.dependencyreader.service;

import com.north47.dependencyreader.domain.Project;
import com.north47.dependencyreader.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findProjectById(Long id) {
        return projectRepository.getOne(id);
    }


}
