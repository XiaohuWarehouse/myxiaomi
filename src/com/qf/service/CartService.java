package com.qf.service;

import com.qf.domain.Cart;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 9:24
 * description:
 */
public interface CartService {
    void add(Cart cart);

    Cart find(Integer uid, int goods_id);

    void modify(Cart cart);

    List<Cart> find(Integer uid);

    void remove(Integer uid, int goods_id);

    void clear(Integer uid);
}
