package com.sunil.springeducation.route;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {

    @GetMapping
    public String getUser() {
        return "users!";
    }
}
