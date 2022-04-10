package com.example.demo.mq.kafka.entity.produce;

import com.example.demo.mq.kafka.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Leo liang
 * @Date 2022/4/10
 */
@Component
public class KafkaProduce {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("hello,leo");
        kafkaTemplate.send("topic-leo", message);
    }



}
