package com.north47.dependencyreader;

import com.north47.dependencyreader.domain.ServiceInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello ELena! You are not so loud";
    }


    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String importUsers(@RequestParam("dependencies") MultipartFile dependencies, @RequestParam("info") MultipartFile info) throws IOException {
        //file.transferTo();
        String content = new String(dependencies.getBytes(), "UTF-8");
        System.out.println(content);
        String content1 = new String(info.getBytes(), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(info.getInputStream()));
        Collection<String> lines = bufferedReader.lines().collect(Collectors.toList());

        ServiceInfo serviceInfo = new ServiceInfo();

        lines.forEach(s -> {
            if(s.contains("version")){
                serviceInfo.setVersion(splitByEquals(s));
            }else if(s.contains("artifactId")){
                serviceInfo.setName(splitByEquals(s));
            }
        });


        System.out.println(content1);
        return "We are ok";
    }

    String splitByEquals(String input){
        return input.split("=")[1];
    }
}
