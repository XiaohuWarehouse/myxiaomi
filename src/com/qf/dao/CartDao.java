package com.qf.dao;

import com.qf.domain.Cart;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 10:22
 * description:
 */
public interface CartDao {
    void insert(Cart cart);

    Cart select(Integer uid, int goods_id);

    void update(Cart cart);

    List<Cart> select(Integer uid);

    void delete(Integer uid, int goods_id);

    void deleteAll(Integer uid);
}
