package com.example.demo.mq.CustomMq.core;

import com.example.demo.mq.CustomMq.demo.Order;
import com.example.demo.mq.CustomMq.producer.MqProduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/leomq")
public class KmqProducer {

    @Autowired
    private KmqBroker broker;

    public KmqProducer(KmqBroker broker) {
        this.broker = broker;
    }

    //send(topic, new KmqMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)))
    @PostMapping("/send")
    public boolean send(String topic, KmqMessage message) throws Exception {
        topic = "leo.test";
        broker.createTopic(topic);
        KmqProducer producer = broker.createProducer();
        Kmq kmq = producer.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return kmq.send(new KmqMessage(null, new Order(100000L + 1, System.currentTimeMillis(), "USD2CNY", 6.52d)));
    }

    @GetMapping("/doSend")
    public boolean send() throws IOException {
        MqProduce mqProduce = new MqProduce();
        mqProduce.send();

        return true;

    }

}
