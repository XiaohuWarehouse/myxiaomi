package com.qf.web.controller;

import com.alibaba.fastjson.JSON;
import com.qf.domain.GoodsType;
import com.qf.service.GoodsTypeService;
import com.qf.service.impl.GoodsTypeServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 11:19
 * description:用于处理用于检索商品类型列表的 AJAX 请求
 */
@WebServlet("/goodstypeservlet")
public class GoodsTypeServlet extends BaseServlet {
    //AJAX获取商品类别列表
    public String goodstypelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletContext application = this.getServletContext();
        String goodsType = (String) application.getAttribute("goodsType");//json
        if (goodsType != null) {
            response.getWriter().write(goodsType);
            System.out.println("从application中获取数据");
            return null;
        }
        //创建业务逻辑对象
        GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
        //调用方法
        List<GoodsType> goodsTypes = goodsTypeService.findByLevel(1);
        System.out.println("从数据库中获取数据");
        //把集合转为json字符串
        String json = JSON.toJSONString(goodsTypes);
        //返回json字符串
        response.getWriter().write(json);
        //把json存入域对象中
        application.setAttribute("goodsType", json);
        return null;
    }
}