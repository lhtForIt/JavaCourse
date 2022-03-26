package com.example.demo.redis.pub_sub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author Leo liang
 * @Date 2022/3/27
 */
@Configuration
public class ChannelMessageConfig {

    @Bean("listenerAdapter")
    public MessageListenerAdapter getListenerAdapter() {
        return new MessageListenerAdapter(new DefaultChannelListener());
    }

    @Bean("container")
    public RedisMessageListenerContainer getContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("leoChannel"));//topic必须和pub定义的channel一样
        return container;
    }


}
