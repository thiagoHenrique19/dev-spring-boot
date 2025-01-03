package com.luv2code.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.RecursiveTask;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){

        //return "plain-login";
        return "fancy-login";
    }

    //add request mapping for/access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied() {

        //return "plain-login";
        return "access-denied";
    }
}
