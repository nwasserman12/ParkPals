package org.launchcode.ParkPals.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Review extends AbstractEntity{

    @NotNull
    @ManyToOne
    private User reviewer;

    @ManyToOne
    private Park park;

    @NotNull
    private Date reviewDate;

    @NotNull
    private ReviewStars numberOfStars;

    public Review(User reviewer, Park park, Date reviewDate, ReviewStars numberOfStars) {
        this.reviewer = reviewer;
        this.park = park;
        this.reviewDate = reviewDate;
        this.numberOfStars = numberOfStars;
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
}
