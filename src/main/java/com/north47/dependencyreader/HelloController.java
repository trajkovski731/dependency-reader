package com.north47.dependencyreader;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class HelloController {

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String importUsers(@RequestParam("dependencies") MultipartFile dependencies, @RequestParam("info") MultipartFile info) throws IOException {
        //file.transferTo();
//        System.out.println("This is the file:" + dependencies.getName());
//        String content = new String(dependencies.getBytes(), "UTF-8");
        System.out.println("This is the file:" + info.getName());
        return "We are ok";
    }
}
