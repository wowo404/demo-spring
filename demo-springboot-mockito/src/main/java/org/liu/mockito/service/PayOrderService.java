package org.liu.mockito.service;

import org.liu.mockito.pojo.po.PayOrder;

import java.util.List;

public interface PayOrderService {
    Long save(PayOrder payOrder);

    void batchSave(List<PayOrder> payOrderList);
}
