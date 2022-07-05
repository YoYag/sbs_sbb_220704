package com.mysite.sbb.User.controller;

import com.mysite.sbb.User.domain.User;
import com.mysite.sbb.User.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<User> users() {
        return userRepository.findAll();
    }
}
