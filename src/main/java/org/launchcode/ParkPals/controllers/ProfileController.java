package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.models.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/add-dog")
    public String processCreateEventForm(@ModelAttribute @Valid Dog newDog,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "user/add-dog";
        }

//        repository.save(newDog);
        return "redirect:";
    }


}
