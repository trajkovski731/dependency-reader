package com.north47.dependencyreader.domain;

public class ServiceInfo {
    private String name;
    private String version;

    public ServiceInfo() { }

    public ServiceInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
