package com.cyj.rabbitmqcenter.sends;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息生产者
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.rabbitmqcenter.sends
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 14:25
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 用于生产消息并发送
     * @param key
     * @param content
     */
    public void send(String key, String content) {
        log.info("key===>{}，sender===>{}", key, content);
        this.rabbitTemplate.convertAndSend(key, content);
    }

}
