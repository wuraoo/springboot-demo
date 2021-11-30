package com.zjj.rabbitmq.routing;

import com.zjj.rabbitmq.config.RoutingConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Routing 路由消费者
 */
@Component
public class RoutingSubscribe {

    // 消费者一：接收error消息
    @RabbitListener(queues = RoutingConfig.DIRECT_QUEUE1)
    public void routingConsumer1(Message msg){
        System.out.println("消费者1收到： " + new String(msg.getBody()));
    }

    // 消费者一：接收info消息
    @RabbitListener(queues = RoutingConfig.DIRECT_QUEUE2)
    public void routingConsumer2(Message msg){
        System.out.println("消费者2收到： " + new String(msg.getBody()));
    }

}
