package com.zjj.rabbitmq.helloworld;

import com.zjj.rabbitmq.config.HelloWorldConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * HelloWorld 接收消息
 */
@Component
public class HelloSubscriber {

    // 该注解用于监听队列中的消息：queues表示队列名称
    @RabbitListener(queues = HelloWorldConfig.QUEUE_NAME)
    public void receiveMsg(Message message){
        System.out.println("收到消息：" + new String(message.getBody()));
    }

}
