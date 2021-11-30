package com.zjj.rabbitmq.helloworld;

import com.zjj.rabbitmq.config.HelloWorldConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * HelloWorld 发送消息
 */
@Component
public class HelloPublisher {
    // 注入rabbit模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param message
     */
    public void sendMsg(String message){
        // 使用模板发送消息：第一个参数为routingKey，第二个参数为消息体
        rabbitTemplate.convertAndSend(HelloWorldConfig.QUEUE_NAME, message);
        System.out.println("send msg: " + message);
    }

}
