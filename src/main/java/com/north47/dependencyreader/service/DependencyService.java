package com.north47.dependencyreader.service;

import com.north47.dependencyreader.domain.Dependency;
import com.north47.dependencyreader.domain.Project;
import com.north47.dependencyreader.domain.ServiceInfo;
import com.north47.dependencyreader.domain.Version;
import com.north47.dependencyreader.repository.DependencyRepository;
import com.north47.dependencyreader.repository.ProjectRepository;
import com.north47.dependencyreader.repository.VersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependencyService {

    private DependencyRepository dependencyRepository;
    private ProjectRepository projectRepository;
    private VersionRepository versionRepository;

    public DependencyService(DependencyRepository dependencyRepository, ProjectRepository projectRepository, VersionRepository versionRepository) {
        this.dependencyRepository = dependencyRepository;
        this.projectRepository = projectRepository;
        this.versionRepository = versionRepository;
    }

    public List<Dependency> findByVersionId(Long id) {
        return dependencyRepository.findAllByVersionId(id);
    }

    public Dependency createDependency(Dependency dependency) {
        return dependencyRepository.save(dependency);
    }

    public Dependency updateDependency(Dependency dependency) {
        return dependencyRepository.save(dependency);
    }

    public void deleteProject(Long id) {
        dependencyRepository.deleteById(id);
    }

    public List<Dependency> findAll() {
        return dependencyRepository.findAll();
    }

    public Dependency findDependencyById(Long id) {
        return dependencyRepository.getOne(id);
    }

    @Transactional
    public void createDependency(MultipartFile dependencies, MultipartFile info) throws IOException {
        BufferedReader dependenciesReader = new BufferedReader(new InputStreamReader(dependencies.getInputStream()));
        List<String> dependenciesData = dependenciesReader.lines().filter(
                dependency -> !dependency.isEmpty()
        ).skip(2L).collect(Collectors.toList());

        ServiceInfo serviceInfo = getServiceInfo(new BufferedReader(new InputStreamReader(info.getInputStream())));

        Project project = projectRepository.findByName(serviceInfo.getName())
                .orElse(projectRepository.save(new Project(null, serviceInfo.getName(), null)));

        Version version = versionRepository.findByVersionAndProject(serviceInfo.getVersion(), project)
                .orElse(versionRepository.save(new Version(null, serviceInfo.getVersion(), null, project)));

//        dependencyRepository.deleteAllByVersionId(version.getId());

        dependenciesData.forEach(
                dependency -> {
                    String[] split = dependency.split(":");
                    dependencyRepository.save(new Dependency(null,
                            split[0].concat(":").concat(split[1]), split[3], version));
                }
        );
    }

    private ServiceInfo getServiceInfo(BufferedReader infoReader) {
        List<String> infoData = infoReader.lines().collect(Collectors.toList());

        ServiceInfo serviceInfo = new ServiceInfo();
        infoData.forEach(s -> {
            if (s.contains("version")) {
                serviceInfo.setVersion(splitByEquals(s));
            } else if (s.contains("artifactId")) {
                serviceInfo.setName(splitByEquals(s));
            }
        });
        return serviceInfo;
    }

    private String splitByEquals(String input) {
        return input.split("=")[1];
    }
}
