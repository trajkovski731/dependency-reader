package com.north47.dependencyreader.resource;

import com.north47.dependencyreader.domain.Dependency;
import com.north47.dependencyreader.service.DependencyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dependencies")
public class DependencyResource {

    private DependencyService dependencyService;

    public DependencyResource(DependencyService dependencyService) {
        this.dependencyService = dependencyService;
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void createDependencies(@RequestParam("dependencies") MultipartFile dependencies,
                                   @RequestParam("info") MultipartFile info) throws IOException {
        dependencyService.createDependency(dependencies, info);
    }

    @GetMapping("/{artifact}/{version}")
    public List<Dependency> getDependenciesForArtifactAndVersion(@PathVariable String artifact,
                                                                 @PathVariable String version) {
        return dependencyService.getForArtifactAndVersionId(artifact, version);

    }

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
