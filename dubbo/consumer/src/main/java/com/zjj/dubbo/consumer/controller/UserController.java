package com.zjj.dubbo.consumer.controller;

import com.zjj.dubbo.pojo.User;
import com.zjj.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // 根据version确定调用的是实现类
    // 使用loadbalance确定使用哪个provider
    @DubboReference(version = "userService1", loadbalance = "random")
    private UserService userService1;

    @DubboReference(version = "userService2")
    private UserService userService2;

    @RequestMapping("/service1")
    public User service1(){
        return userService1.getUser();
    }

    @RequestMapping("/service2")
    public User service2(){
        return userService2.getUser();
    }
}
