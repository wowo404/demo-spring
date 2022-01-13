package org.liu.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    @Test
    void createOrder() {
        ApplicationContext ac = new AnnotationConfigApplicationContext("org.liu");
        OrderService orderService = ac.getBean(OrderService.class);
        System.out.println(orderService);
    }
}