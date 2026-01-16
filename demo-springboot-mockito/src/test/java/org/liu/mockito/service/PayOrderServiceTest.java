package org.liu.mockito.service;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.justing.commons.util.SnowFlake;
import org.liu.mockito.pojo.po.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 启动并发线程测试
 * 1.@Execution(ExecutionMode.CONCURRENT)建议放在类上
 * 2.要在junit-platform.properties中开
 * 3.并发要在容器中，如当前所在的spring容器中
 */
@Execution(ExecutionMode.CONCURRENT)
@SpringBootTest
public class PayOrderServiceTest {

    @Autowired
    private PayOrderService payOrderService;
    private final SnowFlake snowFlake = new SnowFlake(1, 1);

    @TestFactory
    public Collection<DynamicTest> testCreateOrder() {
        List<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DynamicTest dynamicTest = DynamicTest.dynamicTest("createOrder" + i, this::doCreateOrder);
            dynamicTests.add(dynamicTest);
        }
        System.out.println("Running on thread: " + Thread.currentThread().getName());
        return dynamicTests;
    }

    private void doCreateOrder() {
        List<PayOrder> payOrders = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            PayOrder payOrder = new PayOrder();
            payOrder.setPayId(snowFlake.nextId());
            payOrder.setOrderNo(RandomUtil.randomString(20));
            payOrder.setMemberId(RandomUtil.randomLong());
            payOrder.setOpenid(RandomUtil.randomString(20));
            payOrder.setOrderAmount(RandomUtil.randomBigDecimal(new BigDecimal("1000000000")));
            payOrder.setPreferentialAmount(RandomUtil.randomBigDecimal(new BigDecimal("1000000")));
            payOrder.setPayAmount(payOrder.getOrderAmount().subtract(payOrder.getPreferentialAmount()));
            payOrder.setCouponId(RandomUtil.randomLong());
            payOrder.setDepositAmount(RandomUtil.randomBigDecimal(new BigDecimal("1000000")));
            payOrder.setPayMethod(RandomUtil.randomEle(List.of(0, 1, 22)));
            payOrder.setOrderType(RandomUtil.randomEle(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8)));
            payOrder.setTransactionId(RandomUtil.randomString(20));
            payOrder.setTransferBillNo(RandomUtil.randomString(20));
            payOrder.setPaymentNo(RandomUtil.randomString(20));
            payOrder.setScoreOrderId(RandomUtil.randomString(20));
            payOrder.setExpireTime(RandomUtil.randomDay(-200, 0));
            payOrder.setPayResultTime(new Date());
            payOrder.setPayFailureReason(RandomUtil.randomString(20));
            payOrder.setPackageId(RandomUtil.randomLong());
            payOrder.setPackageItemId(RandomUtil.randomLong());
            payOrder.setStationId(RandomUtil.randomLong());
            payOrder.setCabinetId(RandomUtil.randomLong());
            payOrder.setBatteryId(RandomUtil.randomLong());
            payOrder.setModelType(RandomUtil.randomNumbers(2));
            payOrder.setVehicleId(RandomUtil.randomLong());
            payOrder.setAgentId(RandomUtil.randomLong());
            payOrder.setStatus(RandomUtil.randomEle(List.of(0, 1, 2, 3, 4)));
            payOrder.setPayScoreStatus(RandomUtil.randomEle(List.of(0, 1, 2, 3, 4, 5, 6, 7)));
            payOrder.setDelFlag(0);
            payOrder.setTenantId("000000");
            payOrder.setCreateDept(1L);
            payOrder.setCreateBy(1L);
            payOrder.setUpdateBy(1L);
            payOrder.setCreateTime(new Date());
            payOrder.setUpdateTime(new Date());

            payOrders.add(payOrder);

            if (payOrders.size() % 100 == 0) {
                payOrderService.batchSave(payOrders);
                payOrders.clear();
            }
        }
        if (!payOrders.isEmpty()) {
            payOrderService.batchSave(payOrders);
        }
    }

}
