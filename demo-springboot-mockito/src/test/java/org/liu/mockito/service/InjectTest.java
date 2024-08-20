package org.liu.mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.liu.mockito.pojo.po.Order;
import org.liu.mockito.repository.OrderRepository;
import org.liu.mockito.service.impl.OrderServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

/**
 * @Author lzs
 * @Date 2022/11/23 15:20
 **/
@ExtendWith(MockitoExtension.class)
public class InjectTest {

    //OrderService依赖了OrderRepository
    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void test() {
        Order order = new Order();
        order.setId(1L);
        order.setCode("123");
        order.setAmount(new BigDecimal("123.12"));
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        System.out.println(orderService.findById(1L));
    }

}
