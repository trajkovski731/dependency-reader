package com.north47.dependencyreader.repository;

import com.north47.dependencyreader.domain.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, Long> {
}
