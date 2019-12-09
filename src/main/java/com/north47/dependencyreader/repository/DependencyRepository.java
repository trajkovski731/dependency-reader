package com.north47.dependencyreader.repository;

import com.north47.dependencyreader.domain.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependencyRepository extends JpaRepository<Dependency, Long> {
}
