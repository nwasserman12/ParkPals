package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.models.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class ProfileController {

    @GetMapping("/{username}")
    public String profile(Model model, @PathVariable String username) {
        return "user/profile";
    }

    @GetMapping("/add-dog")
    public String displayAddDogForm(Model model) {
        model.addAttribute(new Dog());
        return "user/add-dog";
    }

}
