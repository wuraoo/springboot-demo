package com.zjj.dubbo.provider.serviceImpl;


import com.zjj.dubbo.pojo.User;
import com.zjj.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService(version = "userService2")
public class UserServiceImpl2 implements UserService {
    @Override
    public User getUser() {
        User user = new User();
        user.setName("李四");
        user.setAge(20);
        List<String> list = new ArrayList<>();
        list.add("dog");
        list.add("cat");
        return new User("李四", 20, list);
    }
}
