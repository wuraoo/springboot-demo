package com.zjj.rabbitmq;

import com.zjj.rabbitmq.topic.TopicPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Topic模式测试类
 */
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicPublisher topicPublisher;

    @Test
    public void topicTest(){
        topicPublisher.topicSend();
        /**
         * 输出结果：符合预期
         * send msg : routingKey is user.list.info  （队列1）
         * send msg : routingKey is user.add.error  （队列1）
         * send msg : routingKey is goods.list.info  （队列2）
         * send msg : routingKey is user.address.list.info  （没有队列）
         * topic2 receive msg : msg: routingKey is goods.list.info
         * topic1 receive msg : msg: routingKey is user.list.info
         * topic1 receive msg : msg: routingKey is user.add.error
         */
    }

}
