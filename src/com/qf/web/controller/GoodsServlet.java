package com.qf.web.controller;

import com.qf.domain.Goods;
import com.qf.domain.PageBean;
import com.qf.service.GoodsService;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 14:43
 * description:处理与货物相关的操作
 */
@WebServlet("/goodsservlet")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024)
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

    //添加后台商品
    public String addGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //判断是否登录
        //获取前端数据
        String name = request.getParameter("name");
        String typeid = request.getParameter("typeid");
        String pubdate = request.getParameter("pubdate");
        String price = request.getParameter("price");
        String star = request.getParameter("star");
        String intro = request.getParameter("intro");
        //获取图片
        Part picture = request.getPart("picture");
        //非空验证
        if (StringUtils.isEmpty(name)) {
            request.setAttribute("msg", "商品名称不能为空");
            return "/admin/addGoods.jsp";
        }
        if (StringUtils.isEmpty(typeid)) {
            request.setAttribute("msg", "商品类型不能为空");
            return "/admin/addGoods.jsp";
        }
        if (StringUtils.isEmpty(pubdate)) {
            request.setAttribute("msg", "商品上架时间不能为空");
            return "/admin/addGoods.jsp";
        }
        if (StringUtils.isEmpty(price)) {
            request.setAttribute("msg", "商品价格不能为空");
            return "/admin/addGoods.jsp";
        }
        if (StringUtils.isEmpty(star)) {
            request.setAttribute("msg", "商品热销指数不能为空");
            return "/admin/addGoods.jsp";
        }
        if (StringUtils.isEmpty(intro)) {
            request.setAttribute("msg", "商品介绍不能为空");
            return "/admin/addGoods.jsp";
        }
        int type_id = Integer.parseInt(typeid);
        if (type_id <= 0) {
            request.setAttribute("msg", "请选择合适类型");
            return "/admin/addGoods.jsp";
        }
        String dis = picture.getHeader("Content-Disposition");
        String filename = dis.substring(dis.lastIndexOf("filename=") + 10, dis.length() - 1);
        filename = filename.substring(filename.lastIndexOf("\\") + 1);
        String uuidFileName = null;
        if (!filename.equals("")) {//文件名不为空上传图片
            //保存图片
            String basePath = this.getServletContext().getRealPath("WEB-INF" + File.separator + "images");
            File dir = new File(basePath);
            if (!dir.exists()) {
                dir.mkdirs();//创建多级目录
            }
            //获取后缀
            String ext = filename.substring(filename.lastIndexOf(".") + 1);
            uuidFileName = UUID.randomUUID().toString().replace("-", "");
            uuidFileName = uuidFileName + "." + ext;
            picture.write(basePath+File.separator+uuidFileName);
            picture.delete();
            System.out.println("保存图片成功");
        }
        try {
            GoodsService goodsService = new GoodsServiceImpl();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Goods goods = new Goods(0,name,sdf.parse(pubdate),uuidFileName,new BigDecimal(price),Integer.parseInt(star),intro,type_id);
            goodsService.add(goods);
            response.getWriter().write("<script>alert('添加成功');window.location='admin/addGoods.jsp'</script>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('添加失败');window.location='admin/addGoods.jsp'</script>");
        }
        return null;
    }
}













