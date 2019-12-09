package com.north47.dependencyreader.resource;

import com.north47.dependencyreader.domain.Dependency;
import com.north47.dependencyreader.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dependencies")
public class DependencyResource {

    @Autowired
    private DependencyService dependencyService;


    @PostMapping
    public Dependency saveDependency(@RequestBody Dependency dependency) {
        return dependencyService.createDependency(dependency);
    }

    @GetMapping("/{id}")
    public Dependency getDependencyById(@PathVariable Long id) {
        return dependencyService.findDependencyById(id);
    }

    @GetMapping
    public List<Dependency> getDependencies() {
        return dependencyService.findAll();
    }
}
