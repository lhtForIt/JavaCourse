package com.example.demo.redis.pub_sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Leo liang
 * @Date 2022/3/27
 *
 * pub的调用在JvmApplication里
 *
 */
@Slf4j
public class DefaultChannelListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("=======>message: " + message);
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();

        try {
            String key = new String(channel);
            String value = new String(body);

            log.info("消息频道名称：" + key);
            log.info("消息内容是:" + value);

            //do something for value

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
