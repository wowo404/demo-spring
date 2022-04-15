package org.liu.ssm;

import org.junit.jupiter.api.Test;
import org.liu.ssm.model.User;
import org.liu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {"classpath:applicationContext.xml"})
public class TestSpringMybatis {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = userService.getById(1L);
        System.out.println(user);
    }

}
