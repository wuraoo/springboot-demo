package com.zjj.rabbitmq.topic;

import com.zjj.rabbitmq.config.TopicConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic模式的消费者
 */
@Component
public class TopicSubscribe {

    // 消费者一：绑定队列1
    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE1)
    public void topicConsumer1(Message message){
        System.out.println("topic1 receive msg : " + new String(message.getBody()));
    }

    // 消费者二：绑定队列2
    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE2)
    public void topicConsumer2(Message message){
        System.out.println("topic2 receive msg : " + new String(message.getBody()));
    }

}
