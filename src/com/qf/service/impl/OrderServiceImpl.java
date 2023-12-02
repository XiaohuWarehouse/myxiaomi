package com.qf.service.impl;

import com.qf.dao.OrderDao;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.domain.*;
import com.qf.service.*;
import com.qf.utils.DataSourceUtil;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/29 14:23
 * description:
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void submitOrder(Order order, List<Cart> cartList) {
        //事务操作
        try {
            DataSourceUtil.begin();
            orderDao.add(order);
            for (Cart cart : cartList) {
                OrderDetail orderDetail = new OrderDetail(0, order.getId(), cart.getPid(), cart.getNum(), cart.getMoney());
                orderDao.addDetail(orderDetail);
            }
            CartService cartService = new CartServiceImpl();
            cartService.clear(order.getUid());
            //提交事务
            DataSourceUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                DataSourceUtil.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                DataSourceUtil.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Order> find(Integer uid) {
        List<Order> orderList = orderDao.select(uid);
        if (orderList != null && orderList.size() > 0) {
            AddressService addressService = new AddressServiceImpl();
            List<Address> addressList = addressService.find(uid);
            if (addressList != null) {
                for (Order order : orderList) {
                    for (Address address : addressList) {
                        if (order.getAid() == address.getId()) {
                            order.setAddress(address);
                        }
                    }
                }
            }
        }
        return orderList;
    }

    @Override
    public Order findByOid(String oid) {
        Order order = orderDao.selectByOid(oid);
        if (order != null) {
            AddressService addressService = new AddressServiceImpl();
            List<Address> addressList = addressService.find(order.getUid());
            if (addressList != null) {
                for (Address address : addressList) {
                    if (address.getId() == order.getAid()) {
                        order.setAddress(address);
                    }
                }
            }
        }
        return order;
    }

    @Override
    public List<OrderDetail> findDetail(String oid) {
        List<OrderDetail> orderDetails = orderDao.selectDetail(oid);
        if (orderDetails != null || orderDetails.size() >= 0) {
            GoodsService goodsService = new GoodsServiceImpl();
            for (OrderDetail orderDetail : orderDetails) {
                Goods goods = goodsService.findById(orderDetail.getId());
                orderDetail.setGoods(goods);
            }
        }
        return orderDetails;
    }

    @Override
    public List<Order> adminfind(Integer id) {
        List<Order> orderList = orderDao.select(id);
        if (orderList != null && orderList.size() > 0) {
            UserService userService = new UserServiceImpl();
            List<User> userList = userService.adminfind(id);
            System.out.println(userList);
            if (userList != null) {
                for (Order order : orderList) {
                    for (User user : userList) {
                        if (order.getAid() == user.getId()) {
                            order.setUser(user);
                        }
                    }
                }
            }
        }
        return orderList;
    }

    @Override
    public List<Order> adminorderselect(String string, List<Object> params) {
        List<Order> data = orderDao.selectByPage(string, params);
        return data;
    }

}
