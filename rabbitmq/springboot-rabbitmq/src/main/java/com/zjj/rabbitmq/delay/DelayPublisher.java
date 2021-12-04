package com.zjj.rabbitmq.delay;

import com.zjj.rabbitmq.config.DelayByPluginConfig;
import com.zjj.rabbitmq.config.DelayConfig;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 延时队列 消息生产者
 *     这里需要使用web，因为不是用web的话发送完消息程序就会停止，导致消费者无法接收到消息
 */
@RestController
public class DelayPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送延时30秒的消息
     * @return
     */
    @GetMapping("/delay30")
    public String delayMsgSend30(){
        // 设置交换机
        rabbitTemplate.setExchange(DelayConfig.X_NORMAL_EXCHANGE);
        // 发送延时30s的消息
        rabbitTemplate.convertAndSend(DelayConfig.ROUTING_KEY_A,"消息发送时间：" + new Date().toString());
        return "30000ms delay letter send success";
    }

    /**
     * 发送延时10秒的消息
     * @return
     */
    @GetMapping("/delay10")
    public String delayMsgSend10(){
        // 设置交换机
        rabbitTemplate.setExchange(DelayConfig.X_NORMAL_EXCHANGE);
        // 发送消息
        rabbitTemplate.convertAndSend(DelayConfig.ROUTING_KEY_B,"消息发送时间：" + new Date().toString());
        return "10000ms delay letter send success";
    }

    /**
     * 发送自定义延时的消息
     *         存在问题：消息在队列中是阻塞的——也就是只有前一条数据被处理了（消费或者过期），后一条消息才能被处理
     * @return
     */
    @GetMapping("/delay/{time}")
    public String delayMsgSend(@PathVariable("time")String time){

        // 发送消息
        rabbitTemplate.convertAndSend(
                // 交换机
                DelayConfig.X_NORMAL_EXCHANGE,
                // 路由键
                DelayConfig.ROUTING_KEY_C,
                // 消息
                "消息发送时间：" + new Date().toString(),
                // 参数设置(是一个函数时接口)
                message -> {
                    // 设置消息的延时时长
                    MessageProperties messageProperties = message.getMessageProperties();
                    // 设置为自定义的时间
                    messageProperties.setExpiration(time);
                    return message;
                }
                );
        return time + "ms delay letter send success";
    }

    /*******************************使用插件方式实现延时队列的生产者*****************************************/

    @GetMapping("delay/plugin/{time}")
    public String sendDelayMsg(@PathVariable("time") Integer time){
        // 发布确认类
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("1");

        // 发送消息
        rabbitTemplate.convertAndSend(
                // 交换机
                DelayByPluginConfig.DELAY_EXCHANGE,
                // 路由键
                DelayByPluginConfig.DELAY_KEY,
                // 消息
                "消息发送时间：" + new Date().toString(),
                // 参数设置(是一个函数时接口)
                message -> {
                    // 设置消息的延时时长
                    MessageProperties messageProperties = message.getMessageProperties();
                    // 设置延时时间
                    messageProperties.setDelay(time);
                    messageProperties.setPriority(5);
                    return message;
                },
                // 发布确认消息
                correlationData
        );
        return "delay msg send success";
    }
}
