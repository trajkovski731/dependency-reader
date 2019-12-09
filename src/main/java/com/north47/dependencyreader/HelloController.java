package com.north47.dependencyreader;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello ELena! You are not so loud";
    }


    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String importUsers(@RequestParam("files") MultipartFile file) {
        //file.transferTo();
        System.out.println("This is the file:" + file.getName());
        return "We are ok";
    }
}
