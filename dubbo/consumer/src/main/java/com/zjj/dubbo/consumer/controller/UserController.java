package com.zjj.dubbo.consumer.controller;

import com.zjj.dubbo.pojo.User;
import com.zjj.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // 注意该注解是dubbo的注解
    @Reference
    private UserService userService;

    @RequestMapping("/user")
    public User getUser(){
        return userService.getUser();
    }
}
