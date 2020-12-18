package com.sunil.springeducation.service;

import com.sunil.springeducation.model.Review;
import com.sunil.springeducation.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    };

    public void initializeReviews() {
        Review review1 = Review.builder()
                .userId(1)
                .productId(1)
                .rate(5)
                .review("너무 좋아요! 잘쓰고 있습니다")
                .build();

        Review review2 = Review.builder()
                .userId(1)
                .productId(1)
                .rate(3)
                .review("무난하네요")
                .build();

        this.reviewRepository.save(review1);
        this.reviewRepository.save(review2);
        this.reviewRepository.flush();
    };
}
