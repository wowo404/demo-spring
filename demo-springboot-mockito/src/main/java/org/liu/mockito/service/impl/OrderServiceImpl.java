package org.liu.mockito.service.impl;

import org.liu.mockito.pojo.AddOrderReq;
import org.liu.mockito.pojo.po.Order;
import org.liu.mockito.repository.OrderRepository;
import org.liu.mockito.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author lzs
 * @Date 2022/11/19 11:46
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Long createOrder(AddOrderReq req) {
        return new Random().nextLong();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.getReferenceById(id);
    }
}
