package org.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.demo.service.TestService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestConsumer {

    @Autowired
    private TestService testService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("cloud-queue"), exchange = @Exchange("cloud-exchange"), key = "cloud-routing-key"), concurrency = "10")
    public void receive(String msg){
        log.info("线程ID是：{}", Thread.currentThread().getId());
        log.info("引用对象是：{}", testService);
        log.info("消息是：{}", msg);
        testService.analysis(msg);
    }

}
