package org.liu.ssm.controller;

import lombok.RequiredArgsConstructor;
import org.liu.ssm.model.User;
import org.liu.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("toUser")
    public String toUser(){
        return "user";
    }

    @ResponseBody
    @GetMapping("{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }

}
