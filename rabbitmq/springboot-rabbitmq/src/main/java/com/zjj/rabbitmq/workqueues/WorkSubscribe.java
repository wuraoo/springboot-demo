package com.zjj.rabbitmq.workqueues;

import com.zjj.rabbitmq.config.WorkQueuesConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * WorkQueues 消费者
 */
@Component
public class WorkSubscribe {

    /**
     * 消费者1
     * @param msg
     */
    @RabbitListener(queues = WorkQueuesConfig.QUEUE_NAME)
    public void workConsumer1(Message msg){
        System.out.println("workConsumer1收到消息：" + new String(msg.getBody()));
    }

    /**
     * 消费者2
     * @param msg
     */
    @RabbitListener(queues = WorkQueuesConfig.QUEUE_NAME)
    public void workConsumer2(Message msg){
        System.out.println("workConsumer2收到消息：" + new String(msg.getBody()));
    }

    /**
     * 消费者3
     * @param msg
     */
    @RabbitListener(queues = WorkQueuesConfig.QUEUE_NAME)
    public void workConsumer3(Message msg){
        System.out.println("workConsumer3收到消息：" + new String(msg.getBody()));
    }
}
