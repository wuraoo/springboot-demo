package com.zjj.rabbitmq.delay;

import com.zjj.rabbitmq.config.DelayByPluginConfig;
import com.zjj.rabbitmq.config.DelayConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 延时队列  消息消费者
 */
@Component
public class DelaySubscribe {

    // 监听死信队列
    @RabbitListener(queues = DelayConfig.DEAD_LETTER_QUEUE)
    public void delayConsumer3(Message message){
            System.out.println("死信队列：消息收到时间：" + new Date().toString() + "---------"+ new String(message.getBody()));
    }

    /*******************************使用插件方式实现延时队列的生产者*****************************************/

    @RabbitListener(queues = DelayByPluginConfig.DELAY_QUEUE)
    public void delayConsumerByPlugin(Message message){
        System.out.println("延时队列：消息收到时间：" + new Date().toString() + "---------"+ new String(message.getBody()));
    }

}
