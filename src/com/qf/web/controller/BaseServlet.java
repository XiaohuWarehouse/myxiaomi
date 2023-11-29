package com.qf.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:00
 * description:提供一个通用的Servlet框架
 * 通过不同的请求参数来调用不同的方法
 * 并根据方法的返回值进行相应的转发或重定向操作。
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String methodName = request.getParameter("method");
        //反射
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            if (method != null) {
                String url = (String) method.invoke(this, request, response);
                if (url == null || url.trim().length() == 0) {
                    return;
                }
                //转发或重定向
                if (url.startsWith("redirect:")) {//重定向
                    String redirectURL = url.substring(url.indexOf(":") + 1);//重定向的URL /index.jsp
                    response.sendRedirect(request.getContextPath() + redirectURL);
                } else {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
