package com.zjj.rabbitmq.workqueues;

import com.zjj.rabbitmq.config.WorkQueuesConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * WorkQueue 生产者
 */
@Component
public class WorkPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 循环发送消息
     */
    public void sendMsg(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(WorkQueuesConfig.QUEUE_NAME, "work"+i);
            System.out.println("发送消息：work" + i);
        }
    }


}
