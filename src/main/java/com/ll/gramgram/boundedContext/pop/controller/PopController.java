package com.ll.gramgram.boundedContext.pop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pop")
public class PopController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showIndex() {
        return "usr/pop/index";
    }
}
