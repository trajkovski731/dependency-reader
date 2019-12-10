package com.north47.dependencyreader;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAdminServer
public class DependencyReaderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DependencyReaderApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
        ;
    }

}
