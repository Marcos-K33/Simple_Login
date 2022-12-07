package com.devpredator.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devpredator.demo.requests.RegisterRequest;
import com.devpredator.demo.services.UserService;

@Controller
@RequestMapping("/auth/")
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("login")
    public String formLogin() {
        return "auth/login";
    }

    @GetMapping("register")
    public String formregister(@ModelAttribute("usuario") RegisterRequest request) {
        return "auth/register";
    }

    @PostMapping(path = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(@ModelAttribute("usuario") RegisterRequest request, Model model) {
        String message = userService.registerUser(request);
        model.addAttribute("message", message);
        return message.contains("exitosamente") ? "/auth/login" : "/auth/register";
    }
}
