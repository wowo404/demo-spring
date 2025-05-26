package org.liu.mockito.service;

import org.junit.jupiter.api.*;
import org.liu.mockito.pojo.AddOrderReq;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderService orderService;

    @BeforeEach
    void beforeAll(){
        System.out.println("在所有测试方法之前执行，内部嵌套方法也是一样");
    }

    @Test
    public void createOrder() {
        AddOrderReq req = new AddOrderReq();
        when(orderService.createOrder(req)).thenReturn(100L);
        System.out.println(orderService.createOrder(req));
    }

    @TestClassOrder(ClassOrderer.OrderAnnotation.class)
    @Nested
    class InnerOrderTest {

        @BeforeEach
        void beforeInnerAll(){
            System.out.println("在嵌套类里的所有测试方法之前执行，如果有当前类还有嵌套类，内部嵌套方法也是一样");
        }

        @Order(1)
        @Test
        void findById() {
            org.liu.mockito.pojo.po.Order order = new org.liu.mockito.pojo.po.Order();
            order.setId(1L);
            order.setCode("a1234");
            order.setAmount(new BigDecimal("986532.123456"));
            when(orderService.findById(1L)).thenReturn(order);
            System.out.println(orderService.findById(1L));
        }

        @Order(2)
        @Test
        void findAll() {
            org.liu.mockito.pojo.po.Order order = new org.liu.mockito.pojo.po.Order();
            order.setId(2L);
            order.setCode("a1234");
            order.setAmount(new BigDecimal("986532.123456"));
            List<org.liu.mockito.pojo.po.Order> list = new ArrayList<>(1);
            list.add(order);
            when(orderService.findAll()).thenReturn(list);
            System.out.println(orderService.findAll());
        }

    }
}
