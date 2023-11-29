package com.qf.dao;

import com.qf.domain.Order;
import com.qf.domain.OrderDetail;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/29 14:28
 * description:
 */
public interface OrderDao {
    void add(Order order);

    void addDetail(OrderDetail orderDetail);

    List<Order> select(Integer uid);

    Order selectByOid(String oid);

    List<OrderDetail> selectDetail(String oid);
}
