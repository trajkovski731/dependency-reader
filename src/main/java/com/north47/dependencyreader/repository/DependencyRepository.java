package com.north47.dependencyreader.repository;

import com.north47.dependencyreader.domain.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DependencyRepository extends JpaRepository<Dependency, Long> {

    List<Dependency> findAllByVersionId(Long versionId);
}
