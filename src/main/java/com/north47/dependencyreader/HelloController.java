package com.north47.dependencyreader;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        System.out.println(content1);
        return "We are ok";
    }
}
