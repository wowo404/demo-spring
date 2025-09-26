package org.liu.mockito.repository;

import org.liu.mockito.pojo.po.PayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lzs
 * @Date 2022/11/23 15:16
 **/
@Repository
public interface PayOrderRepository extends JpaRepository<PayOrder, Long> {
}