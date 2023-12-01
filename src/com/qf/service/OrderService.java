package com.qf.service;

import com.qf.domain.Cart;
import com.qf.domain.Order;
import com.qf.domain.OrderDetail;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/29 14:23
 * description:
 */
public interface OrderService {
    void submitOrder(Order order, List<Cart> cartList);

    List<Order> find(Integer uid);

    Order findByOid(String oid);

    List<OrderDetail> findDetail(String oid);

    List<Order> adminfind(Integer id);
}
