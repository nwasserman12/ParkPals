package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.models.Dog;
import org.launchcode.ParkPals.models.DogActivity;
import org.launchcode.ParkPals.models.DogTemperament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class ProfileController {

    @Autowired
    private DogRepository dogRepository;

    @GetMapping("/{username}")
    public String profile(Model model, @PathVariable String username) {
        return "user/profile";
    }

    @GetMapping("/add-dog")
    public String displayAddDogForm(Model model) {
        model.addAttribute(new Dog());
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        return "user/add-dog";
    }

    @PostMapping("/add-dog")
    public String processCreateEventForm(@ModelAttribute @Valid Dog newDog,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "user/add-dog";
        }

        dogRepository.save(newDog);
        return "redirect:/add-dog"; //add-dog is placeholder for user profile
    }


}
