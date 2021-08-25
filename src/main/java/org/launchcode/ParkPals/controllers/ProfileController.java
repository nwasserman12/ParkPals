package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.data.UserRepository;
import org.launchcode.ParkPals.models.Dog;
import org.launchcode.ParkPals.models.DogActivity;
import org.launchcode.ParkPals.models.DogTemperament;
import org.launchcode.ParkPals.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class ProfileController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping()
    public String profile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("user", authenticationController.getUserFromSession(session));
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
    public String processCreateDogForm(@ModelAttribute @Valid Dog newDog,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            return "user/add-dog";
        }

        dogRepository.save(newDog);
        return "redirect:/user";
    }


}
