package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.data.ReviewRepository;
import org.launchcode.ParkPals.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @GetMapping("{userId}/review/create-review")
    public String displayCreateReviewForm(Model model, HttpServletRequest request, @ModelAttribute Park park){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Review());
        model.addAttribute("stars", ReviewStars.values());
        model.addAttribute("user", user);
        model.addAttribute("park", park);
        return "review/create-review";
    }

    @PostMapping("{userId}/review/create-review")
    public String processCreateReviewForm(@ModelAttribute @Valid Review newReview, @ModelAttribute Park park,
                                          Errors errors, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if(errors.hasErrors()) {
            model.addAttribute("stars", ReviewStars.values());
            model.addAttribute("user", user);
            model.addAttribute("park", park);
            return "redirect:/review/create-review";
        }
        newReview.setPark(park);
        newReview.setReviewer(user);
        reviewRepository.save(newReview);
        return "redirect:/review/review";
    }


}
