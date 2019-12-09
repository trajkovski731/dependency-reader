package com.north47.dependencyreader.service;

import com.north47.dependencyreader.domain.Dependency;
import com.north47.dependencyreader.repository.DependencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependencyService {

    @Autowired
    private DependencyRepository dependencyRepository;

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


}
