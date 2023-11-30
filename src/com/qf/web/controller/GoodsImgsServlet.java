package com.qf.web.controller;

import com.qf.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/30 14:13
 * description:
 */
@WebServlet("/goodsImgs")
public class GoodsImgsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获取前端数据
        String pic = request.getParameter("pic");
        if (StringUtils.isEmpty(pic)) {
            request.setAttribute("msg", "");
            return;
        }
        String basePath = this.getServletContext().getRealPath("WEB-INF" + File.separator + "images" + File.separator + pic);
        File dir = new File(basePath);
        if (dir.exists()) {
            //只有目录存在才可以读取图片
            FileInputStream fis = new FileInputStream(dir);
            ServletOutputStream os = response.getOutputStream();
            byte[] buf = new byte[1024*8];
            int len;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf,0,len);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}