package com.zjj.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 插件方式构建延时队列
 */
@Configuration
public class DelayByPluginConfig {

    // 延时交换机
    public static final String DELAY_EXCHANGE = "delay_exchange";
    // 延时队列
    public static final String DELAY_QUEUE = "delay_queue";
    // 路由键
    public static final String DELAY_KEY = "delay.info";

    /**
     * 创建自定义延时交换机
     * @return
     */
    @Bean
    public CustomExchange delayExchange(){
        HashMap<String, Object> arguments = new HashMap<>();
        // 设置延时类型
        arguments.put("x-delayed-type","direct");
        /**
         * 参数：
         *      交换机名称
         *      交换机类型
         *      是否持久化
         *      是否自动删除
         *      其他参数
         */
        return new CustomExchange(
                DELAY_EXCHANGE,
                "x-delayed-message",
                false,
                false,
                arguments)
                ;
    }

    // 队列
    @Bean
    public Queue delayQueue(){
        return new Queue(DELAY_QUEUE);
    }

    // 绑定
    @Bean
    public Binding delayBindingQueue(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_KEY).noargs();
    }
}
