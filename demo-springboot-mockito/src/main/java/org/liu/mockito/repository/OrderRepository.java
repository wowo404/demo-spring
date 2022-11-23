package org.liu.mockito.repository;

import org.liu.mockito.pojo.po.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lzs
 * @Date 2022/11/23 15:16
 **/
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}