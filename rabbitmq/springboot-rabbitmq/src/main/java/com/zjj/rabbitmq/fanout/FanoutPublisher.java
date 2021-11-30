package com.zjj.rabbitmq.fanout;

import com.zjj.rabbitmq.config.FanoutConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Fanout 生产者
 */
@Component
public class FanoutPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void fanoutSend(){
        // 设置交换机，将会将当前消息 直接 发送到指定的交换机
        rabbitTemplate.setExchange(FanoutConfig.FANOUT_EXCHANGE);
        for (int i = 0; i < 3; i++) {
            rabbitTemplate.convertAndSend("第 " + i + "条消息");
            System.out.println("发送第 " + i + "条消息");
        }
    }

}
