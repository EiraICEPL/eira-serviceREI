package com.hummersoft.eira.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("/eira")
public class RegistrationController {

    @GetMapping("/register")
    public String sayHello(){
        return "Hello Eira!!!";
    }
}
