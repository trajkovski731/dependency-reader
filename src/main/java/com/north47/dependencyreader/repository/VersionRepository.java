package com.north47.dependencyreader.repository;

import com.north47.dependencyreader.domain.Project;
import com.north47.dependencyreader.domain.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Long> {
    Optional<Version> findByVersionAndProject(String version, Project project);
}
