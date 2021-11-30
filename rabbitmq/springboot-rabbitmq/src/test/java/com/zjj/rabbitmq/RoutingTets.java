package com.zjj.rabbitmq;

import com.zjj.rabbitmq.routing.RoutingPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Routing 测试
 */
@SpringBootTest
public class RoutingTets {

    @Autowired
    private RoutingPublisher routingPublisher;

    @Test
    public void routingTest(){
        routingPublisher.directSend();
        /**
         * 输出结果：实现路由的发送与接收
         * error send success
         * info send success
         * 消费者1收到： ERROR: out of memory
         * 消费者2收到： info: insert into ......
         */
    }
}
