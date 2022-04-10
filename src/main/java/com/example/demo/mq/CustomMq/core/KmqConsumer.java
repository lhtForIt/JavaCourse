package com.example.demo.mq.CustomMq.core;

import com.example.demo.mq.CustomMq.consumer.MqConsumer;
import com.example.demo.mq.CustomMq.demo.Order;
import com.example.demo.mq.CustomMq.producer.MqProduce;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/leomq1")
public class KmqConsumer<T> {

    @Autowired
    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll(long timeout) {
        return kmq.poll(timeout);
    }

    public KmqMessage<T> poll(long timeout, int offset) {

        return kmq.poll(timeout);
    }

    @PostMapping("/rec")
    public KmqMessage<T> poll(Integer offset) throws Exception {
        String topic = "leo.test";
        KmqConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);
        this.kmq = this.broker.findKmq(topic);
        KmqMessage<Order> message = null;
        try {
            message = kmq.poll();
            if (null != message) {
                System.out.println(message.getBody());
            }
        } catch (Exception e) {
            throw new RuntimeException("poll 异常");
        }
        return (KmqMessage<T>) message;
    }
    @GetMapping("/doRecive")
    public boolean recive() throws IOException {
        MqConsumer mqConsumer = new MqConsumer();
        mqConsumer.revice();

        return true;

    }

}
