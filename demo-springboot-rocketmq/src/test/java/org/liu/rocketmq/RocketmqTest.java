package org.liu.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RocketmqTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void test() {
        rocketMQTemplate.convertAndSend("topic-liu", "Are you ok?");
    }
}
