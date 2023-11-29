package com.qf.web.controller;

import com.qf.domain.*;
import com.qf.service.AddressService;
import com.qf.service.CartService;
import com.qf.service.OrderService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.service.impl.CartServiceImpl;
import com.qf.service.impl.OrderServiceImpl;
import com.qf.utils.RandomUtils;
import com.qf.utils.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:00
 * description:处理与 Web 应用程序中的订单相关的请求。
 */
@WebServlet("/orderservlet")
public class OrderServlet extends BaseServlet {
    //添加订单
    public String addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        String aid = request.getParameter("aid");
        if (StringUtils.isEmpty(aid)) {
            request.setAttribute("msg", "地址不能为空");
            return "message.jsp";
        }
        //调用业务逻辑
        OrderService orderService = new OrderServiceImpl();
        CartService cartService = new CartServiceImpl();
        List<Cart> cartList = cartService.find(user.getId());
        if (cartList == null || cartList.size() == 0) {
            request.setAttribute("msg", "购物车不能为空");
            return "/message.jsp";
        }
        BigDecimal money = new BigDecimal(0);
        for (Cart cart : cartList) {
            money = money.add(cart.getMoney());
        }
        try {
            String oid = RandomUtils.createOrderId();
            Order order = new Order(oid, user.getId(), money, "1", new Date(), Integer.parseInt(aid));
            orderService.submitOrder(order, cartList);//cartList取商品信息
            request.setAttribute("order", order);
            return "/orderSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "添加订单失败");
            return "/message.jsp";
        }
    }

    //处理查看订单的请求。
    public String getOrderView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //调用业务逻辑
        try {
            CartService cartService = new CartServiceImpl();
            List<Cart> cartList = cartService.find(user.getId());
            AddressService addressService = new AddressServiceImpl();
            List<Address> addList = addressService.find(user.getId());
            request.setAttribute("cartList", cartList);
            request.setAttribute("addList", addList);
            return "/order.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "预览订单失败：" + e.getMessage());
            return "/message.jsp";
        }
    }

    //获取订单列表
    public String getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //调用业务逻辑
        try {
            OrderService orderService = new OrderServiceImpl();
            List<Order> orderList = orderService.find(user.getId());
            request.setAttribute("orderList", orderList);
            return "/orderList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "查询订单失败：：" + e.getMessage());
            return "/message.jsp";
        }
    }

    //根据订单编号查询订单详情
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        String oid = request.getParameter("oid");
        if (StringUtils.isEmpty(oid)) {
            request.setAttribute("msg", "订单编号不能为空");
            return "message.jsp";
        }
        try {
            OrderService orderService = new OrderServiceImpl();
            Order order = orderService.findByOid(oid);
            List<OrderDetail> orderDetails = orderService.findDetail(oid);
            request.setAttribute("order", order);
            request.setAttribute("orderDetails", orderDetails);
            return "/orderDetail.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "查看订单详情失败" + e.getMessage());
            return "/message.jsp";
        }
    }
}