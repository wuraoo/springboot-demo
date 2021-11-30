package com.zjj.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * WorkQueues 配置类
 */
@Configuration
public class WorkQueuesConfig {

    // 队列名称
    public static final String QUEUE_NAME = "work_queue";

    /**
     * 创建工作队列
     * @return
     */
    @Bean
    public Queue workQueue(){
        /**
         * 队列名称
         * 是否持久化
         * 是否共享
         * 是否自动删除
         */
        return new Queue(QUEUE_NAME, false, false, false);
    }

}
