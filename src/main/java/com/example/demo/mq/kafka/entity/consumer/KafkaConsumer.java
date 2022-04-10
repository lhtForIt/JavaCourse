package com.example.demo.mq.kafka.entity.consumer;

import com.example.demo.mq.kafka.entity.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Leo liang
 * @Date 2022/4/10
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"topic-leo"})
    public void recive(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Message message = (Message) kafkaMessage.get();
            System.out.println("id是==>"+message.getId());
            System.out.println("msg是==>"+message.getMsg());
        }
    }


}
