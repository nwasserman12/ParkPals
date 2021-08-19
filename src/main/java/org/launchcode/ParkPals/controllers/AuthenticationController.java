package org.launchcode.ParkPals.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        return "register";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        return "login";
    }
}

