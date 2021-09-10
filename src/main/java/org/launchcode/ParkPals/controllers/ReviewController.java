package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

@Entity
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;


}
