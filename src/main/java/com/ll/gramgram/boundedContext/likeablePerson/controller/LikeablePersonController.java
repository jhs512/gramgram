package com.ll.gramgram.boundedContext.likeablePerson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/likeablePerson")
@RequiredArgsConstructor
public class LikeablePersonController {
    @GetMapping("/manage")
    public String showManage() {
        return "usr/likeablePerson/manage";
    }
}
