package com.zjj.dubbo.provider.serviceImpl;


import com.zjj.dubbo.pojo.User;
import com.zjj.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

// 注意给注解是dubbo的注解，而不应是spring的注解
// version为版本，在消费者处根据版本号调用
@DubboService(version = "userService1")
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return new User("张三",15,null);
    }
}
