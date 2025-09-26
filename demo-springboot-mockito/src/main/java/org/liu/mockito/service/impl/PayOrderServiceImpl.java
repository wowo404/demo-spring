package org.liu.mockito.service.impl;

import lombok.RequiredArgsConstructor;
import org.liu.mockito.pojo.po.PayOrder;
import org.liu.mockito.repository.PayOrderRepository;
import org.liu.mockito.service.PayOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PayOrderServiceImpl implements PayOrderService {

    private final PayOrderRepository payOrderRepository;

    @Override
    public Long save(PayOrder payOrder) {
        payOrderRepository.save(payOrder);
        return payOrder.getPayId();
    }

    /**
     * hibernate批量执行的配置关键点：
     * 1.批量保存的实体类必须实现Persistable接口，手动set了id，此接口告诉hibernate此对象是否是新对象
     * 2.savaAll方法要在事务中
     * 3.配置文件要配置batch_size，一般配置在20-50
     * 4.MySQL数据库的url要配置rewriteBatchedStatements=true
     * -------结果-------
     * 原来插入10000条数据耗时： 8分钟左右
     * 批量插入10000条数据耗时： 23秒左右
     */
    @Override
    public void batchSave(List<PayOrder> payOrderList) {
        payOrderRepository.saveAll(payOrderList);
    }

}
