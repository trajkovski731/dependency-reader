package com.north47.dependencyreader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello ELena! You are not so loud";
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public String createDependencyFile(MultipartFile file) {
        //file.transferTo();
        System.out.println("This is the file:" + file.getName());
        return "We are ok";
    }
}
