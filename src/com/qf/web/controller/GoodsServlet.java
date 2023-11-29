package com.qf.web.controller;

import com.qf.domain.Goods;
import com.qf.domain.PageBean;
import com.qf.service.GoodsService;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 14:43
 * description:处理与货物相关的操作
 */
@WebServlet("/goodsservlet")
public class GoodsServlet extends BaseServlet {
    //根据指定的参数检索商品列表
    public String getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String goodsName = request.getParameter("goodsName");
        System.out.println("goodsName = " + goodsName);
        int page_num = 1;
        int page_size = 8;
        try {
            if (!StringUtils.isEmpty(pageNum)) {
                page_num = Integer.parseInt(pageNum);
                if (page_num < 1) {
                    page_num = 1;
                }
            }
            if (!StringUtils.isEmpty(pageSize)) {
                page_size = Integer.parseInt(pageSize);
                if (page_size < 1) {
                    page_size = 8;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder where = new StringBuilder(" where 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (!StringUtils.isEmpty(typeId)) {
            where.append(" and typeid=? ");
            params.add(typeId);
        }
        if (!StringUtils.isEmpty(goodsName)) {
            where.append(" and name like ? ");
            params.add("%" + goodsName + "%");
        }
        if (params.size() <= 0) {
            return "/goodsList.jsp";
        }
        GoodsService goodsService = new GoodsServiceImpl();
        try {
            PageBean<Goods> pageBean = goodsService.findByPage(page_num, page_size, where.toString(), params);
            System.out.println("pageBean = " + pageBean);
            request.setAttribute("typeId", typeId);
            request.setAttribute("goodsName", goodsName);
            request.setAttribute("pageBean", pageBean);
            return "/goodsList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/index.jsp";
    }

    //根据给定的“id”参数检索特定的商品项目
    public String getGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        if (StringUtils.isEmpty(id)) {
            request.setAttribute("msg", "商品id不能为空");
            return "/message.jsp";
        }
        GoodsService goodsService = new GoodsServiceImpl();
        try {
            Goods goods = goodsService.findById(Integer.parseInt(id));
            request.setAttribute("goods", goods);
            return "/goodsDetail.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "查询失败" + e.getMessage());
            return "/message.jsp";
        }
    }
}
