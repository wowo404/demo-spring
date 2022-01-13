package org.liu.service.impl;

import org.liu.dao.OrderDao;
import org.liu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void createOrder() {
        orderDao.save();
    }
}
