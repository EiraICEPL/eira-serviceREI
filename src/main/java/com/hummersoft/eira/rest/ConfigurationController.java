package com.hummersoft.eira.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/eira")
public class ConfigurationController {

    @GetMapping("/config")
    public String testConfig(){
        return "Welcome to Eira Configuration Service";
    }

}
