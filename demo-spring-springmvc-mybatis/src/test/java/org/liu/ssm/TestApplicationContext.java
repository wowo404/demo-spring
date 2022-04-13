package org.liu.ssm;

import org.junit.jupiter.api.Test;
import org.liu.ssm.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplicationContext {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        userService.getById(1L);
    }

}
