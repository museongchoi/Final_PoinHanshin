package com.project.poinhanshin.controller;

import com.project.poinhanshin.domain.member.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;



@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        return "home";
    }

}

