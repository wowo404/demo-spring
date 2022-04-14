package org.liu.ssm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.liu.ssm.model.User;
import org.liu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSpringMybatis {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = userService.getById(1L);
        System.out.println(user);
    }

}
