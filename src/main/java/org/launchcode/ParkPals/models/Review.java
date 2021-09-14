package org.launchcode.ParkPals.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Review extends AbstractEntity{


    @ManyToOne
    private User reviewer;

    @ManyToOne
    private Park park;

    @NotNull(message= "What is today's date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date reviewDate;

    @NotNull
    private ReviewStars numberOfStars;

    @NotNull
    @Size(max=500, message = "Must not exceed 500 Characters")
    private String description;


    public Review(User reviewer, Park park, Date reviewDate, ReviewStars numberOfStars, String description) {
        this.reviewer = reviewer;
        this.park = park;
        this.reviewDate = reviewDate;
        this.numberOfStars = numberOfStars;
        this.description = description;
    }

    public Review(){}

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public ReviewStars getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(ReviewStars numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
