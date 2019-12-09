package com.north47.dependencyreader.repository;

import com.north47.dependencyreader.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
