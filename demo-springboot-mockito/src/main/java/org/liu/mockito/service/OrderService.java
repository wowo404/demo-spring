package org.liu.mockito.service;

import org.liu.mockito.pojo.AddOrderReq;
import org.liu.mockito.pojo.po.Order;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/11/19 11:45
 **/
public interface OrderService {
    Long createOrder(AddOrderReq req);
    Order findById(Long id);

    List<Order> findAll();
}