package com.zjj.rabbitmq.topic;

import com.zjj.rabbitmq.config.TopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Topic 模式下的生产者
 */
@Component
public class TopicPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void topicSend(){
        // 指定当前生产者发送消息到创建的topic交换机上
        rabbitTemplate.setExchange(TopicConfig.TOPIC_EXCHANGE);
        /**
         * 参数1：路由键
         * 参数2：消息
         *      根据topic匹配规则将发送到queue1中
         */
        rabbitTemplate.convertAndSend("user.list.info","msg: routingKey is user.list.info");
        System.out.println("send msg : routingKey is user.list.info");
        // 按规则将发送到queue1中
        rabbitTemplate.convertAndSend("user.add.error","msg: routingKey is user.add.error");
        System.out.println("send msg : routingKey is user.add.error");
        // 按匹配规则将发送到queue2中
        rabbitTemplate.convertAndSend("goods.list.info","msg: routingKey is goods.list.info");
        System.out.println("send msg : routingKey is goods.list.info");
        // 按规则将没有队列匹配
        rabbitTemplate.convertAndSend("user.address.list.info", "msg: routingKey is user.address.list.info");
        System.out.println("send msg : routingKey is user.address.list.info");
    }

}
