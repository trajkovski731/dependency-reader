package com.north47.dependencyreader.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Interceptors implements WebMvcConfigurer {
    private final CorsInterceptor corsInterceptor;

    @Autowired
    public Interceptors(CorsInterceptor corsInterceptor) {
        this.corsInterceptor = corsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.corsInterceptor).addPathPatterns("/**");
    }
}
