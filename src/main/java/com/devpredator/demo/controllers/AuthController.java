package com.devpredator.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    @GetMapping("login")
    public String formLogin() {
        return "auth/login";
    }
}
