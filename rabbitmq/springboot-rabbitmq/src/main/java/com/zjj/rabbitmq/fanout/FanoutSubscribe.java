package com.zjj.rabbitmq.fanout;

import com.zjj.rabbitmq.config.FanoutConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Fanout 消费者
 */
@Component
public class FanoutSubscribe {

    // 消费者1使用队列1接收消息
    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE1)
    public void fanoutReceive1(Message message){
        System.out.println("消费者1接收到：" + new String(message.getBody()));
    }

    // 消费者2使用队列2接收消息
    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE2)
    public void fanoutReceive2(Message message){
        System.out.println("消费者2接收到：" + new String(message.getBody()));
    }

}
