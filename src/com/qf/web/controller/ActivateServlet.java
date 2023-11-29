package com.qf.web.controller;

import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Base64Utils;
import com.qf.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 7:58
 * description:处理用户帐户的激活过程
 */
@WebServlet("/activate")
public class ActivateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("UTF-8");
        //1获取前端数据
        String email = request.getParameter("e");
        String code = request.getParameter("c");
        //判断
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(code)) {
            request.setAttribute("msg", "激活失败");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //解码
        email = Base64Utils.decode(email);
        code = Base64Utils.decode(code);
        //激活
        UserService userService = new UserServiceImpl();
        try {
            userService.active(email, code);
            request.setAttribute("msg", "激活成功");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "激活失败");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
