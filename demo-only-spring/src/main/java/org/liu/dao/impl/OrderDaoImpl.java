package org.liu.dao.impl;

import org.liu.dao.OrderDao;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Override
    public void save() {
        System.out.println("success");
    }
}
