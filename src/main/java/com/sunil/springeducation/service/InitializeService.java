package com.sunil.springeducation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InitializeService {
    private final UserService userService;
    private final ProductService productService;
    private final SaleService saleService;
    private final ReviewService reviewService;

    @Autowired
    public InitializeService(UserService userService, ProductService productService, SaleService saleService, ReviewService reviewService) {
        this.userService = userService;
        this.productService = productService;
        this.saleService = saleService;
        this.reviewService = reviewService;
    };

    public void initializer() {
          this.userService.initializeUsers();
          this.productService.initializeProducts();
          this.saleService.initializeSales();
          this.reviewService.initializeReviews();
    };
};
