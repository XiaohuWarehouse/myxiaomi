package com.qf.web.filter;

import com.qf.domain.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter", value = "/index.jsp")
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    //过滤功能
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1强转
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        //2过滤
        User user=(User) request.getSession().getAttribute("user");
        //已经登录 没有自动登录 ******
        if (user != null) {
            chain.doFilter(request, response);
            return;
        }
        //如果有用户
        Cookie[] cookies = request.getCookies();
        if (cookies != null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userInfo")){
                    String userInfo = cookie.getValue();
                    userInfo= Base64Utils.decode(userInfo);//bbb#123456
                    String[] arr = userInfo.split("#");
                    if (arr.length==2) {
                        String username=arr[0];//bbb
                        String password=arr[1];//123456
                        System.out.println(username+"\t"+password);
                        UserService userService=new UserServiceImpl();
                        try {
                            User u=userService.login(username,password);
                            request.getSession().setAttribute("user",u);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                            //清除Cookie
                            Cookie deleteCookie=new Cookie("userInfo","");
                            deleteCookie.setPath(request.getContextPath());
                            deleteCookie.setMaxAge(0);
                            response.addCookie(deleteCookie);
                        }
                    }
                }
            }
        }
        //3放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
