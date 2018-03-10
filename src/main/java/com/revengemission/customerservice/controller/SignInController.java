package com.revengemission.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping(value = "/login")
    public String signIn(Model model) {
        return "login";
    }

}
