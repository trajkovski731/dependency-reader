package com.north47.dependencyreader.resource;

import com.north47.dependencyreader.domain.Version;
import com.north47.dependencyreader.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/versions")
@CrossOrigin(origins = "*")
public class VersionResource {

    @Autowired
    private VersionService versionService;


    @PostMapping
    public Version saveVersion(@RequestBody Version version) {
        return versionService.createVersion(version);
    }

    @GetMapping("/{id}")
    public Version getVersionById(@PathVariable Long id) {
        return versionService.findVersionById(id);
    }

    @GetMapping
    public List<Version> getVersions() {
        return versionService.findAll();
    }
}
