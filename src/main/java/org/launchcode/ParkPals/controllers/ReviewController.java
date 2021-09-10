package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.data.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ParkRepository parkRepository;

    @GetMapping("{userId}/review")
    public String ListOfReviews(Model model){
        model.addAttribute("review", reviewRepository.findAll());
        return "review/review";
    }



}
