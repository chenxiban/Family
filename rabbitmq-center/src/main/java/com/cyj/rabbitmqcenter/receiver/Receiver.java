package com.cyj.rabbitmqcenter.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.rabbitmqcenter.Receivers
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 14:28
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void process(String hello) {
        log.info("receiver===>{}",hello);
    }

}
