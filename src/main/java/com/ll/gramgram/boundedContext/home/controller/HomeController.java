package com.ll.gramgram.boundedContext.home.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String showMain() {
        return "redirect:/pop";
    }
}
