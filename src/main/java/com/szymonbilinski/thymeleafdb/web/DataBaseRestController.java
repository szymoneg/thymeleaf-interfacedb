package com.szymonbilinski.thymeleafdb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataBaseRestController {

    @GetMapping
    public String get(){
        return "serverTest";
    }
}
