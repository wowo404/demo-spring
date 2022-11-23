package org.liu.mockito.service;

import org.junit.jupiter.api.Test;
import org.liu.mockito.pojo.AddOrderReq;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderService orderService;

    @Test
    public void createOrder() {
        AddOrderReq req = new AddOrderReq();
        when(orderService.createOrder(req)).thenReturn(100L);
        System.out.println(orderService.createOrder(req));
    }
}
