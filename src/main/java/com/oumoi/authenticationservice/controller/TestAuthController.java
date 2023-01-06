package com.oumoi.authenticationservice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class TestAuthController {


    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String greeting(){
        return "Hello there.";
    }
}
