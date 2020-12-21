package com.sunil.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column
    private int userId;

    @Column
    private int productId;

    @Column
    private int rate;

    @Column
    private String review;

    @Builder
    public Review(int productId, int userId, int rate, String review) {
        this.productId = productId;
        this.userId = userId;
        this.rate = rate;
        this.review = review;
    };

    @Override
    public String toString() {
        return String.format(
                "Review[userId=%d, productId=%d, rate=%d, review='%s'",
                this.userId, this.productId, this.rate, this.review
        );
    };
};
