package com.zjj.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    // 直接交换机名称
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    // 消费者一的队列名称
    public static final String TOPIC_QUEUE1 = "topic_queue1";
    // 消费者二的队列名称
    public static final String TOPIC_QUEUE2 = "topic_queue2";
    // 路由键1名称
    public static final String TOPIC_KEY_1 = "user.*.info";
    // 路由键2名称
    public static final String TOPIC_KEY_2 = "#.error";
    // 路由键3名称
    public static final String TOPIC_KEY_3 = "goods.#";


    /**
     * 申明一个topic交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 创建第一个queue
     * @return
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1);
    }

    /**
     * 创建第一个queue
     * @return
     */
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2);
    }

    /**
     * 绑定第一个队列的key1
     * @return
     */
    @Bean
    public Binding bingingQueue1(){
        return BindingBuilder
                .bind(topicQueue1())
                .to(topicExchange())
                // routingKey为 "user.*.ingo" 表示接收user下的所有info消息
                .with(TOPIC_KEY_1);
    }

    /**
     * 绑定第一个队列的key2
     * @return
     */
    @Bean
    public Binding bingingQueue2(){
        return BindingBuilder
                .bind(topicQueue1())
                .to(topicExchange())
                // routingKey为 "#.error" 表示接收所有error消息
                .with(TOPIC_KEY_2);
    }

    /**
     * 绑定第二个队列的key1
     * @return
     */
    @Bean
    public Binding bingingQueue3(){
        return BindingBuilder
                .bind(topicQueue2())
                .to(topicExchange())
                // routingKey为 "goods.#" 表示接收所有goods相关消息
                .with(TOPIC_KEY_3);
    }



}
