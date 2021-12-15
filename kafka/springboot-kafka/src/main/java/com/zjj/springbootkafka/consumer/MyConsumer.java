package com.zjj.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    private final String TOPIC = "my-topic";

    /**
     * 监听主题
     * @param record  接收到的消息记录
     * @param ack    确认消息
     */
    @KafkaListener(
            // 消费组名称
            groupId = "my-group1",
            // 消费主题
            topicPartitions = {
            // 消费主题1的0、1分区
            @TopicPartition(topic = "topic1", partitions = {"0","1"}),
            // 消费主题2的0分区；分区1的偏移量为10
            @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "10"))
            },
            // 该消费组中创建3个消费者
            concurrency = "3"
    )
    public void receiveMsg(ConsumerRecord<String,String> record, Acknowledgment ack){
        // 业务逻辑
        System.out.println(record.topic() + "\t" + record.value());
        // 手动确认
        ack.acknowledge();
    }
}
