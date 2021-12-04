package com.zjj.rabbitmq;

import com.zjj.rabbitmq.delay.DelayPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 延时消息测试
 */
@SpringBootTest
public class DelayTest {

    @Autowired
    private DelayPublisher delayPublisher;

    @Test
    public void delayyTest(){
        // 该测试请查看：lazy/LazyPublisher类  ，该类为web，这样方便程序一直启动以接收延时消息

        /**
         * 结果输出： 延时时间正好是10s和30s
         * 死信队列：消息收到时间：Wed Dec 01 21:15:47 CST 2021---------消息发送时间：Wed Dec 01 21:15:37 CST 2021
         * 死信队列：消息收到时间：Wed Dec 01 21:16:07 CST 2021---------消息发送时间：Wed Dec 01 21:15:37 CST 2021
         */
    }


}
