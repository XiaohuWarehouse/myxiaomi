package com.qf.web.controller;

import com.qf.domain.Cart;
import com.qf.domain.Goods;
import com.qf.domain.User;
import com.qf.service.CartService;
import com.qf.service.GoodsService;
import com.qf.service.impl.CartServiceImpl;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 9:10
 * description:处理与购物车相关的操作
 */
@WebServlet("/cartservlet")
public class CartServlet extends BaseServlet {
    //将商品添加到用户购物车
    public String addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("number");
        if (StringUtils.isEmpty(goodsId)) {
            request.setAttribute("msg", "商品id不能为空");
            return "/message.jsp";
        }
        int num = 1;
        if (!StringUtils.isEmpty(number)) {
            try {
                num = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            int goods_id = Integer.parseInt(goodsId);
            CartService cartService = new CartServiceImpl();
            GoodsService goodsService = new GoodsServiceImpl();
            Goods goods = goodsService.findById(goods_id);

            Cart cart = cartService.find(user.getId(), goods_id);
            if (cart != null) {
                cart.setNum(cart.getNum() + num);
                cart.setMoney(goods.getPrice().multiply(new BigDecimal(cart.getNum())));
                cartService.modify(cart);
            } else {
                cart = new Cart(user.getId(), goods_id, num, goods.getPrice().multiply(new BigDecimal(num)));
                cartService.add(cart);
            }
            return "redirect:/cartSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "添加购物车失败：" + e.getMessage());
            return "/message.jsp";
        }
    }

    //检索用户购物车
    public String getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //调用业务逻辑
        try {
            CartService cartService = new CartServiceImpl();
            List<Cart> cartList = cartService.find(user.getId());
            //购物车数据存储与转发
            request.setAttribute("cartList", cartList);
            return "/cart.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "查看购物车失败：" + e.getMessage());
            return "/message.jsp";
        }
    }

    //使用 AJAX 在用户购物车中添加和更新商品
    public String addCartAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //从前端页面获取商品信息
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("number");
        //校验，判断商品是否为空
        if (StringUtils.isEmpty(goodsId)) {
            request.setAttribute("msg", "商品id不能为空");
            return "/message.jsp";
        }
        //调用业务逻辑
        try {
            int num = Integer.parseInt(number);
            int goods_id = Integer.parseInt(goodsId);
            CartService cartService = new CartServiceImpl();
            if (num == 0) {//删除商品
                cartService.remove(user.getId(), goods_id);
            } else if (num == 1 || num == -1) {
                //查找商品
                Cart cart = cartService.find(user.getId(), goods_id);
                if (cart != null) {
                    GoodsService goodsService = new GoodsServiceImpl();
                    Goods goods = goodsService.findById(goods_id);
                    cart.setNum(cart.getNum() + num);
                    cart.setMoney(goods.getPrice().multiply(new BigDecimal(cart.getNum())));
                    cartService.modify(cart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "更新购物车失败：" + e.getMessage());
            return "/message.jsp";
        }
        return null;
    }

    //使用 AJAX 清除用户购物车
    public String clearCartAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //调用业务逻辑
        try {
            CartService cartService = new CartServiceImpl();
            cartService.clear(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "清空购物车失败：" + e.getMessage());
            return "/message.jsp";
        }
        return null;
    }
}
