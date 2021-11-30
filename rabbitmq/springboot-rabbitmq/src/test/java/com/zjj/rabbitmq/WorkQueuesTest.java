package com.zjj.rabbitmq;

import com.zjj.rabbitmq.workqueues.WorkPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * WorkQueues 测试
 */
@SpringBootTest
public class WorkQueuesTest {

    @Autowired
    private WorkPublisher workPublisher;

    @Test
    public void WorkTest(){
        workPublisher.sendMsg();
        /**
         * 结果：以轮询的方式
         * 发送消息：work0
         * 发送消息：work1
         * 发送消息：work2
         * 发送消息：work3
         * 发送消息：work4
         * 发送消息：work5
         * 发送消息：work6
         * 发送消息：work7
         * 发送消息：work8
         * 发送消息：work9
         * workConsumer2收到消息：work0
         * workConsumer1收到消息：work1
         * workConsumer3收到消息：work2
         * workConsumer2收到消息：work3
         * workConsumer1收到消息：work4
         * workConsumer3收到消息：work5
         * workConsumer2收到消息：work6
         * workConsumer1收到消息：work7
         * workConsumer3收到消息：work8
         * workConsumer2收到消息：work9
         */
    }
}
