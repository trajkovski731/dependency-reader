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

    private static final int NUMBER_OF_DEPENDENCIES_COLUMS = 5;
    private static final String DEPENDENCY_REGEX = ":";
    private static final int GROUP_ID = 0;
    private static final int ARTIFACT_ID = 1;
    public static final int VERSION = 3;
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
        ).collect(Collectors.toList());

        ServiceInfo serviceInfo = getServiceInfo(info);
        Project project = getOrCreateProject(serviceInfo);
        Version version = getOrCreateVersion(serviceInfo, project);
        dependencyRepository.deleteAllByVersionId(version.getId());

        dependenciesData.forEach(dependency -> createDependencies(version, dependency));
    }

    private void createDependencies(Version version, String dependency) {
        String[] dependencyProperties = dependency.split(DEPENDENCY_REGEX);
        if (dependencyProperties.length == NUMBER_OF_DEPENDENCIES_COLUMS)
            dependencyRepository.save(new Dependency(null,
                    dependencyProperties[GROUP_ID].concat(DEPENDENCY_REGEX).concat(dependencyProperties[ARTIFACT_ID]),
                    dependencyProperties[VERSION], version));
    }

    private Version getOrCreateVersion(ServiceInfo serviceInfo, Project project) {
        return versionRepository.findByVersionAndProject(serviceInfo.getVersion(), project)
                .orElseGet(() -> versionRepository.save(new Version(null, serviceInfo.getVersion(), null, project)));
    }

    private Project getOrCreateProject(ServiceInfo serviceInfo) {
        return projectRepository.findByName(serviceInfo.getName())
                .orElseGet(() -> projectRepository.save(new Project(null, serviceInfo.getName(), null)));
    }

    private ServiceInfo getServiceInfo(MultipartFile info) throws IOException {
        BufferedReader infoReader = new BufferedReader(new InputStreamReader(info.getInputStream()));
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

    public List<Dependency> getForArtifactAndVersionId(String artifact, String versionName) {
        Project project = projectRepository.findByName(artifact).orElseThrow
                (() -> new RuntimeException("Can't find the project"));
        return project.getVersions().stream()
                .filter(version -> version.getVersion().equals(versionName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such version"))
                .getDependencies();


    }
}
