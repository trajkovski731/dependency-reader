package com.north47.dependencyreader.resource;

import com.north47.dependencyreader.domain.Project;
import com.north47.dependencyreader.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectResource {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) throws Throwable {
        return projectService.findProjectById(id).orElseThrow((Supplier<Throwable>) () -> new Exception("DOesn't exist"));
    }


    @GetMapping
    public List<Project> getProjects() {
        return projectService.findAll();
    }
}
