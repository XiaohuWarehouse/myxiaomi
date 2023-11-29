package com.qf.service.impl;

import com.qf.dao.CartDao;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.domain.Cart;
import com.qf.domain.Goods;
import com.qf.service.CartService;
import com.qf.service.GoodsService;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 9:24
 * description:
 */
public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();

    @Override
    public void add(Cart cart) {
        cartDao.insert(cart);
    }

    @Override
    public Cart find(Integer uid, int goods_id) {
        return cartDao.select(uid, goods_id);
    }

    @Override
    public void modify(Cart cart) {
        cartDao.update(cart);
    }

    @Override
    public List<Cart> find(Integer uid) {
        List<Cart> cartList = cartDao.select(uid);
        if (cartList != null && cartList.size() > 0) {
            GoodsService goodsService = new GoodsServiceImpl();
            for (Cart cart : cartList) {
                Goods goods = goodsService.findById(cart.getPid());
                cart.setGoods(goods);
            }
        }
        return cartList;
    }

    @Override
    public void remove(Integer uid, int goods_id) {
        cartDao.delete(uid, goods_id);
    }

    @Override
    public void clear(Integer uid) {
        cartDao.deleteAll(uid);
    }
}
