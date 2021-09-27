package org.launchcode.ParkPals.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class ContactController {
    @GetMapping("/contact")
    public String contact() {
        return "/contact";
    }
}
