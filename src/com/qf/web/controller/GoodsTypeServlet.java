package com.qf.web.controller;

import com.alibaba.fastjson.JSON;
import com.qf.domain.Goods;
import com.qf.domain.GoodsType;
import com.qf.service.GoodsService;
import com.qf.service.GoodsTypeService;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.utils.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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

    //查看后台商品分类
    public String getGoodsTypeaddselect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在这里执行搜索逻辑，根据name和pubdate查询商品列表
        try {
            GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
            List<GoodsType> goodsTypeList = goodsTypeService.find();
            System.out.println(goodsTypeList);
            // 将查询结果存储在request属性中
            request.getSession().setAttribute("goodsTypeList", goodsTypeList);
            return "redirect:/admin/addGoodsType.jsp";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //后台添加商品种类
    public String getGoodsTypeadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String typename = request.getParameter("typename");
        String goodsParent = request.getParameter("goodsParent");
        System.out.println(goodsParent);
        //非空验证
        if (StringUtils.isEmpty(typename)) {
            request.setAttribute("msg", "种类名称不能为空");
            return "/admin/addGoodsType.jsp";
        }
        try {
            GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
//            GoodsType goodsType = new GoodsType(0,goodsParent.getClass());
//            goodsTypeService.add(goodsType);
            response.getWriter().write("<script>alert('添加成功');window.location='admin/addGoodsType.jsp'</script>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('添加失败');window.location='admin/addGoodsType.jsp'</script>");
        }
        return null;
    }

    //查看后台商品分类
    public String getGoodsTypeshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在这里执行搜索逻辑，根据name和pubdate查询商品列表
        try {
            GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
            List<GoodsType> goodsTypeList = goodsTypeService.find();
            System.out.println(goodsTypeList);
            // 将查询结果存储在request属性中
            request.getSession().setAttribute("goodsTypeList", goodsTypeList);
            return "redirect:/admin/showGoodsType.jsp";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}