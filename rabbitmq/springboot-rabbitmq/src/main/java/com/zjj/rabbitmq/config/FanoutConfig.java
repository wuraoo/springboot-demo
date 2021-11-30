package com.zjj.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout （Publish/Subscribe）扇出配置类：
 *        扇出与前面两个示例不一样，需要创建交换机和绑定
 */
@Configuration
public class FanoutConfig {

    // 扇出交换机名称
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    // 消费者一的队列名称
    public static final String FANOUT_QUEUE1 = "fanout_queue1";
    // 消费者二的队列名称
    public static final String FANOUT_QUEUE2 = "fanout_queue2";


    // 声明一个扇出交换机
    @Bean
    public FanoutExchange PSFanoutExchange(){
        /**
         * 交换机名称
         * 是否持久化
         * 是否自动删除
         */
        return new FanoutExchange(FANOUT_EXCHANGE,false,false);
    }

    // 声明第一个队列
    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE1, false, false, false);
    }

    // 声明第二个队列
    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE2, false, false, false);
    }

    // 绑定第一个队列与交换机
    @Bean
    public Binding bindingFanoutQueue1(){
        return BindingBuilder
                // 绑定创建第一个队列
                .bind(fanoutQueue1())
                // 绑定扇出队列
                .to(PSFanoutExchange());
    }

    // 绑定第二个队列与交换机
    @Bean
    public Binding bindingFanoutQueue2(){
        return BindingBuilder
                // 绑定创建第二个队列
                .bind(fanoutQueue2())
                // 绑定扇出队列
                .to(PSFanoutExchange());
    }
}
