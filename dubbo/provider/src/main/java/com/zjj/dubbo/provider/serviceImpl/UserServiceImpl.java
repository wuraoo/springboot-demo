package com.zjj.dubbo.provider.serviceImpl;


import com.zjj.dubbo.pojo.User;
import com.zjj.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Service;

// 注意该注解是dubbo的注解，而不是spring的注解
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return new User("张三", 15, null);
    }
}
