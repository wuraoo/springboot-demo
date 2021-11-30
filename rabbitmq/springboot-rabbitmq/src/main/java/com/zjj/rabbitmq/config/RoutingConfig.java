package com.zjj.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Routing (Direct)直接模式 配置类
 */
@Configuration
public class RoutingConfig {

    // 直接交换机名称
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    // 消费者一的队列名称
    public static final String DIRECT_QUEUE1 = "direct_queue1";
    // 消费者二的队列名称
    public static final String DIRECT_QUEUE2 = "direct_queue2";
    // 路由键1名称
    public static final String ROUTING_KEY_1 = "error";
    // 路由键2名称
    public static final String ROUTING_KEY_2 = "info";

    // 声明一个直接交换机
    @Bean
    public DirectExchange directExchange(){
        /**
         * 交换机名称
         * 是否持久化
         * 是否自动删除
         */
        return new DirectExchange(DIRECT_EXCHANGE, false, false);
    }

    // 声明队列一
    @Bean
    public Queue directQueue1(){
        return new Queue(DIRECT_QUEUE1);
    }

    // 声明队列二
    @Bean
    public Queue directQueue2(){
        return new Queue(DIRECT_QUEUE2);
    }

    // 队列一绑定，包含路由
    @Bean
    public Binding bindingDirectQueue1(){
        return BindingBuilder
                // 绑定队列一
                .bind(directQueue1())
                // 绑定直接交换机
                .to(directExchange())
                // 指定路由键
                .with(ROUTING_KEY_1);
    }

    // 队列二绑定，包含路由
    @Bean
    public Binding bindingDirectQueue2(){
        return BindingBuilder
                // 绑定队列二
                .bind(directQueue2())
                // 绑定直接交换机
                .to(directExchange())
                // 指定路由键
                .with(ROUTING_KEY_2);
    }

}
