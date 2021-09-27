package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.data.ReviewRepository;
import org.launchcode.ParkPals.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ParkRepository parkRepository;

    @GetMapping("/review")
    public String ListOfReviews(Model model){
        model.addAttribute("review", reviewRepository.findAll());
        return "review/review";
    }

    @GetMapping("/review/create-review/{placeId}")
    public String displayCreateReviewForm(@PathVariable String placeId, Model model, HttpServletRequest request, Review review){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Review());
        model.addAttribute(placeId);
        model.addAttribute("stars", ReviewStars.values());
        model.addAttribute("user", user);
        Park park = parkRepository.findByPlaceId(placeId);
        model.addAttribute("park", park);
        return "review/create-review";
    }

    @PostMapping("/review/create-review/{placeId}")
    public String processCreateReviewForm(@ModelAttribute @Valid Review review, @PathVariable String placeId,
                                          Errors errors, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Park park = parkRepository.findByPlaceId(placeId);
        if(errors.hasErrors()) {
            model.addAttribute("stars", ReviewStars.values());
            model.addAttribute("user", user);
            model.addAttribute("park", park);
            return "review/create-review";
        }
        review.setPark(park);
        review.setReviewer(user);
        reviewRepository.save(review);
        return "redirect:/review";
    }

}
