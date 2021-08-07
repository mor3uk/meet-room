package com.test.meetroom.controller;

import com.test.meetroom.entity.User;
import com.test.meetroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/generateAvatar")
    public String generateAvatar(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            userService.generateAvatar(user);
        } catch (IOException e) {
            return "redirect:/?errorImageGeneration";
        }
        return "redirect:/?successImageGeneration";
    }
}
