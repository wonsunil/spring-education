package com.sunil.route;

import com.sunil.service.ProductService;
import com.sunil.service.ReviewService;
import com.sunil.service.SaleService;
import com.sunil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/initialize")
public class InitializeRoute {
    private final UserService userService;
    private final ProductService productService;
    private final SaleService saleService;
    private final ReviewService reviewService;

    @Autowired
    public InitializeRoute(UserService userService, ProductService productService, SaleService saleService, ReviewService reviewService) {
        this.userService = userService;
        this.productService = productService;
        this.saleService = saleService;
        this.reviewService = reviewService;
    };

    @GetMapping("")
    public void initialize() {
        this.userService.initializeUsers();
        this.productService.initializeProducts();
        this.saleService.initializeSales();
        this.reviewService.initializeReviews();
    };
};
