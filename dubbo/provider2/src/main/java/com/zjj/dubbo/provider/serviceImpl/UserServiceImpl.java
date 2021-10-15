package com.zjj.dubbo.provider.serviceImpl;

import com.zjj.dubbo.pojo.User;
import com.zjj.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "userService1",loadbalance = "random")
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return new User("provider2", 2, null);
    }
}
