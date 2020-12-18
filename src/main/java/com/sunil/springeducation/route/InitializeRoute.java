package com.sunil.springeducation.route;

import com.sunil.springeducation.service.InitializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/initialize")
public class InitializeRoute {
    private final InitializeService initializeService;

    @Autowired
    public InitializeRoute(InitializeService initializeService) {
        this.initializeService = initializeService;
    };

    @GetMapping("")
    public void initialize() {
        this.initializeService.initializer();
    };
};
