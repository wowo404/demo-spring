package org.liu.ssm.service.impl;

import org.liu.ssm.model.User;
import org.liu.ssm.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(Long id) {
        System.out.println("success");
        return null;
    }
}
