package com.example.demo.mq.CustomMq.core;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
//        this.queue = new LinkedBlockingQueue(capacity);
        this.queue = new SimpleArrayQueue<>(capacity);
    }

    private String topic;

    private int capacity;

    private int consumerIndex;

//    private LinkedBlockingQueue<KmqMessage> queue;
    private SimpleArrayQueue<KmqMessage> queue;

    public boolean send(KmqMessage message) throws Exception {
        System.out.println("produce发送："+message.getBody().toString());
        return queue.offer(message);
    }

    public KmqMessage poll() throws Exception {
        consumerIndex++;
        return queue.poll();
    }
    public KmqMessage poll(int offset) throws Exception {
        KmqMessage kmqMessage = queue.poll(offset);
        consumerIndex = offset + 1;
        System.out.println("consumer接收消息："+kmqMessage.getBody().toString());
        return kmqMessage;
    }

    @SneakyThrows
    public KmqMessage poll(long timeout) {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }

}
