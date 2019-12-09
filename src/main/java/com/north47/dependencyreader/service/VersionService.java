package com.north47.dependencyreader.service;

import com.north47.dependencyreader.domain.Project;
import com.north47.dependencyreader.domain.Version;
import com.north47.dependencyreader.repository.ProjectRepository;
import com.north47.dependencyreader.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;

    public Version createVersion(Version version) {
        return versionRepository.save(version);
    }

    public Version updateVersion(Version version) {
        return versionRepository.save(version);
    }

    public void deleteVersion(Long id) {
        versionRepository.deleteById(id);
    }

    public List<Version> findAll() {
        return versionRepository.findAll();
    }

    public Version findVersionById(Long id) {
        return versionRepository.getOne(id);
    }


}
