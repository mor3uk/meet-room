package com.test.meetroom.controller;

import com.test.meetroom.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String meetRoom(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "index";
    }
}
