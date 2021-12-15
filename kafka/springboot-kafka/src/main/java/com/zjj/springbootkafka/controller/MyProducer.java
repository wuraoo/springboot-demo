package com.zjj.springbootkafka.controller;

import com.zjj.springbootkafka.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyProducer {

    private String TOPIC = "my-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 发送消息
    @GetMapping("send")
    public String sendMsg(User user){
        // 选择主题、分区、key、value发送数据
        kafkaTemplate.send(TOPIC, 0, "key", user.toString());
        return "success";
    }
}
