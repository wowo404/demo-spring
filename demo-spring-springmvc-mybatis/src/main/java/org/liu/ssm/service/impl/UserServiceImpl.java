package org.liu.ssm.service.impl;

import lombok.RequiredArgsConstructor;
import org.liu.ssm.dao.UserDao;
import org.liu.ssm.model.User;
import org.liu.ssm.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
