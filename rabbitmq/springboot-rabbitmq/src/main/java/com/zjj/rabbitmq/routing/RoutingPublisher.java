package com.zjj.rabbitmq.routing;

import com.zjj.rabbitmq.config.RoutingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Direct 路由生产者
 */
@Component
public class RoutingPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void directSend(){
        // 发送第一个消息：routingKey为error
        rabbitTemplate.convertSendAndReceive(
                RoutingConfig.DIRECT_EXCHANGE,
                RoutingConfig.ROUTING_KEY_1,
                "ERROR: out of memory"
        );
        System.out.println("error send success");

        // 发送第二个消息：routingKey为info
        rabbitTemplate.convertSendAndReceive(
                RoutingConfig.DIRECT_EXCHANGE,
                RoutingConfig.ROUTING_KEY_2,
                "info: insert into ......"
        );
        System.out.println("info send success");
    }




}
