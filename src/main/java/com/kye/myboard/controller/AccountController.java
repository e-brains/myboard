package com.kye.myboard.controller;

import com.kye.myboard.model.User;
import com.kye.myboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "account/loginForm";
    }

    @GetMapping("/registerForm")
    public String registerForm(){
        return "/account/registerForm";
    }

    @PostMapping("/register")
    public String register(User user){

        userService.회원가입저장(user);
        return"redirect:/index";
    }



}
