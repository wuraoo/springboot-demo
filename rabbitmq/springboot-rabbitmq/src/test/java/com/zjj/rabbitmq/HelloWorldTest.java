package com.zjj.rabbitmq;

import com.zjj.rabbitmq.helloworld.HelloPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * HelloWorld 模式测试
 */
@SpringBootTest
class HelloWorldTest {

    @Autowired
    private HelloPublisher helloPublisher;

    @Test
    void helloTest() {
        // 发消息
        helloPublisher.sendMsg("hello world");
        /**
         * 结果：
         *       send msg: hello world
         *      收到消息：hello world
         */
    }

}
