package com.zjj.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用配置类来创建队列和交换机
 */
@Configuration
public class HelloWorldConfig {
    // 队列名称
    public static final String QUEUE_NAME = "simple_queue";

    @Bean
    public Queue simpleQueue(){
        // 创建一个队列
        return new Queue(QUEUE_NAME);
    }

}
