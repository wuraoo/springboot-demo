package com.zjj.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 延时队列相关组件配置
 */
@Configuration
public class DelayConfig {
    // 普通交换机
    public static final String X_NORMAL_EXCHANGE = "X";
    // 死信交换机
    public static final String Y_DEAD_EXCHANGE = "Y";
    // 延时队列1
    public static final String Delay_QUEUE_A = "LQA";
    // 延时队列2
    public static final String Delay_QUEUE_B = "LQB";
    // 普通队列
    public static final String NORMAL_QUEUE_C = "QC";
    // 死信队列
    public static final String DEAD_LETTER_QUEUE = "DQ";
    // 延时队列1的路由Key
    public static final String ROUTING_KEY_A = "XtoA";
    // 延时队列2的路由key
    public static final String ROUTING_KEY_B = "XtoB";
    // 普通队列
    public static final String ROUTING_KEY_C = "XtoC";
    // 死信队列的路由Key
    public static final String DEAD_LETTER_ROUTING_KEY = "LtoD";



    // 声明普通交换机X
    @Bean
    public DirectExchange normalExchange(){
        return new DirectExchange(X_NORMAL_EXCHANGE);
    }
    // 声明死信交换机
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange(Y_DEAD_EXCHANGE);
    }
    // 声明延时队列A
    @Bean
    public Queue delayQueueA(){
        // 声明队列时需要的参数
        HashMap<String, Object> arguments = new HashMap<>();
        // 指定死信消息需要转发到的交换机
        arguments.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        // 设置死信routingKey
        arguments.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        // 设置TTL:30s
        arguments.put("x-message-ttl", 30000);
        // 声明延时队列并设置参数
        return QueueBuilder.durable(Delay_QUEUE_A).withArguments(arguments).build();
    }
    // 声明延时队列B
    @Bean
    public Queue delayQueueB(){
        // 声明队列时需要的参数
        HashMap<String, Object> arguments = new HashMap<>();
        // 指定死信消息需要转发到的交换机
        arguments.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        // 设置死信routingKey
        arguments.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        // 设置TTL:10s
        arguments.put("x-message-ttl", 10000);
        // 声明延时队列并设置参数
        return QueueBuilder.durable(Delay_QUEUE_B).withArguments(arguments).build();
    }
    // 声明普通队列，不需要设置TTL
    @Bean
    public Queue normalQueueC(){
        // 声明队列时需要的参数
        HashMap<String, Object> arguments = new HashMap<>();
        // 指定死信消息需要转发到的交换机
        arguments.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        // 设置死信routingKey
        arguments.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        // 声明普通队列
        return QueueBuilder.durable(NORMAL_QUEUE_C).withArguments(arguments).build();
    }
    // 声明死信队列
    @Bean
    public Queue deadQueue(){
        return new Queue(DEAD_LETTER_QUEUE);
    }
    // 绑定队列A与普通交换机
    @Bean
    public Binding bindingQueueA(){
        // 绑定并设置路由键
        return BindingBuilder.bind(delayQueueA()).to(normalExchange()).with(ROUTING_KEY_A);
    }
    // 绑定队列B与普通交换机
    @Bean
    public Binding bindingQueueB(){
        // 绑定并设置路由键
        return BindingBuilder.bind(delayQueueB()).to(normalExchange()).with(ROUTING_KEY_B);
    }
    // 绑定普通队列
    @Bean
    public Binding bindingQueueC(){
        return BindingBuilder.bind(normalQueueC()).to(normalExchange()).with(ROUTING_KEY_C);
    }
    // 绑定死信队列与死信交换机
    @Bean
    public Binding bindingDeadQueue(){
        // 绑定并设置路由键
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }
}
