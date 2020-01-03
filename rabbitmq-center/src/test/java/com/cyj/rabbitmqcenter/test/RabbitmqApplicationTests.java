package com.cyj.rabbitmqcenter.test;

import com.cyj.rabbitmqcenter.receiver.Receiver;
import com.cyj.rabbitmqcenter.sends.Sender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description: 测试消息使用
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.rabbitmqcenter
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-03 14:31
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Test
    public void contextLoads() {
        receiver.process("你是谁？");
    }

    @Test
    public void hello() throws Exception{
        sender.send("hello","Hello Xiao Zai Zi Now ===>" + new Date());
    }

}
