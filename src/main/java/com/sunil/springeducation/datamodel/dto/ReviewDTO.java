package com.sunil.springeducation.datamodel.dto;

import com.sunil.springeducation.model.Review;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private int reviewId;
    private int userId;
    private int productId;
    private int rate;
    private String review;

    public ReviewDTO(Review review) {
        this.productId = review.getProductId();
        this.userId = review.getUserId();
        this.rate = review.getRate();
        this.review = review.getReview();
    };

    @Override
    public String toString() {
        return String.format(
                "ReviewDTO[userId=%d, productId=%d, rate=%d, review='%s'",
                this.userId, this.productId, this.rate, this.review
        );
    };
};