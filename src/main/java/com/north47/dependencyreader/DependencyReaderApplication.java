package com.north47.dependencyreader;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class DependencyReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DependencyReaderApplication.class, args);
    }

}
