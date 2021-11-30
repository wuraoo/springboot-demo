package com.zjj.rabbitmq;

import com.zjj.rabbitmq.fanout.FanoutPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Fanout 测试
 */
@SpringBootTest
public class FanoutTest {

    @Autowired
    private FanoutPublisher fanoutPublisher;

    @Test
    public void fanoutTest(){
        fanoutPublisher.fanoutSend();

        /**
         * 输出结果： 效果为广播
         * 发送第 0条消息
         * 发送第 1条消息
         * 发送第 2条消息
         * 消费者1接收到：第 0条消息
         * 消费者2接收到：第 0条消息
         * 消费者2接收到：第 1条消息
         * 消费者1接收到：第 1条消息
         * 消费者2接收到：第 2条消息
         * 消费者1接收到：第 2条消息
         */
    }
}
