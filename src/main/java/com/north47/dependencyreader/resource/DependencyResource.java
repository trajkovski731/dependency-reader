package com.north47.dependencyreader.resource;

import com.north47.dependencyreader.domain.Dependency;
import com.north47.dependencyreader.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dependencies")
@CrossOrigin(origins = "*")
public class DependencyResource {

    @Autowired
    private DependencyService dependencyService;


    @GetMapping("/{id}")
    public List<Dependency> getDependenciesForProjectAndVersion(@PathVariable Long id) {
        return dependencyService.findByVersionId(id);
    }

    @PostMapping
    public Dependency saveDependency(@RequestBody Dependency dependency) {
        return dependencyService.createDependency(dependency);
    }

    @GetMapping
    public List<Dependency> getDependencies() {
        return dependencyService.findAll();
    }
}
