package com.zjj.rabbitmq.utils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 该类为RabbitMQ的发布确认的处理类，
 * 通过实现RabbitMqTemplate中接口并实现其方法以处理每条消息是否发送成功
 *
 *
 */
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    // 注入模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 将该类设置回模板
    @PostConstruct
    public void init(){
        // 发布确认
        rabbitTemplate.setConfirmCallback(this);
        // 消息回退
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 回调方法
     * @param correlationData 回调消息的ID及相关消息
     * @param ack          是否发送成功（交换机是否收到消息）：是——true；否——false
     * @param s          失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (correlationData != null)
        if(ack){
            System.out.println(correlationData.getId() + "消息发送成功");
        }else{
            System.out.println(correlationData.getId() + " 消息发送失败！失败原因：" + s);
        }
    }

    /**
     * 当消息传递过程中不可达目的地的时候就会回退给生产者
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        System.out.println("消息：" + new String(message.getBody()) + "被交换机：" +exchange+ "退回,路由键：" + routingKey+ "。  原因为：" + replyText);
    }


}
