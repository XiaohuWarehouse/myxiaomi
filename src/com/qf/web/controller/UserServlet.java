package com.qf.web.controller;

import cn.dsna.util.images.ValidateCode;
import com.qf.domain.Address;
import com.qf.domain.User;
import com.qf.service.AddressService;
import com.qf.service.UserService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Base64Utils;
import com.qf.utils.RandomUtils;
import com.qf.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 17:52
 * description:用于管理 Web 应用程序中的用户注册、登录和地址信息。
 */
@WebServlet("/userservlet")
public class UserServlet extends BaseServlet {
    //用户注册功能
    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取前端数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        //非空验证
        if (StringUtils.isEmpty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "/register.jsp";
        }
        if (StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "/register.jsp";
        }
        if (!password.equals(repassword)) {
            request.setAttribute("msg", "两次输入密码不一致");
            return "/register.jsp";
        }
        if (StringUtils.isEmpty(email)) {
            request.setAttribute("msg", "邮箱不能为空");
            return "/register.jsp";
        }

        //2调用业务逻辑
        UserService userService = new UserServiceImpl();
        boolean b = userService.checkUserName(username);
        if (b) {//true 已存在
            request.setAttribute("msg", "用户名已存在");
            return "/register.jsp";
        }
        //友情提示 如果不写邮箱激活 flag=1
        User user = new User(0, username, password, email, gender, 0, 0, RandomUtils.createActiveCode());
        try {
            userService.register(user);
            //3转发重定向
            return "redirect:/registerSuccess.jsp";//重定向
        } catch (Exception e) {
            e.printStackTrace();//程序员 跟踪错误信息
            request.setAttribute("msg", "注册失败");
            return "/register.jsp";
        }

    }

    //用户登录功能
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取前端数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode");
        String auto = request.getParameter("auto");

        //校验
        if (StringUtils.isEmpty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "/message.jsp";
        }
        if (StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "/message.jsp";
        }
        if (StringUtils.isEmpty(vcode)) {
            request.setAttribute("msg", "验证码不能为空");
            return "/message.jsp";
        }
        //校验验证码
        String code = (String) request.getSession().getAttribute("vcode");
        if (!vcode.equalsIgnoreCase(code)) {
            request.setAttribute("msg", "验证码错误");
            return "/message.jsp";
        }
        //2调用业务逻辑
        try {
            UserService userService = new UserServiceImpl();
            User user = userService.login(username, password);
            //把用户存入session中
            request.getSession().setAttribute("user", user);

            //完成自动登录
            System.out.println(auto);//勾选 on  不勾选 null
            if (auto != null) {
                //自动登录
                String userInfo = username + "#" + password;//bbb#123456
                //Base64处理
                userInfo = Base64Utils.encode(userInfo);
                Cookie cookie = new Cookie("userInfo", userInfo);
                //设置路径
                cookie.setPath(request.getContextPath());
                //有效期
                cookie.setMaxAge(14 * 24 * 60 * 60);//两周自动登录
                //设置httponly
                cookie.setHttpOnly(true);
                //回写
                response.addCookie(cookie);
            }
            //3转发重定向
            return "redirect:/index.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户登录失败" + e.getMessage());
            return "/message.jsp";
        }
    }

    //获取验证码功能
    public String code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        //1创建验证码对象
        ValidateCode validateCode = new ValidateCode(100, 28, 4, 20);
        //2把验证码存入session中
        request.getSession().setAttribute("vcode", validateCode.getCode());
        System.out.println(validateCode.getCode());
        //3响应浏览器
        validateCode.write(response.getOutputStream());
        return null;
    }

    //验证用户名是否存在
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        if (StringUtils.isEmpty(username)) {
            response.getWriter().write("1");//不可用
            return null;
        }
        UserService userService = new UserServiceImpl();
        boolean b = userService.checkUserName(username);
        if (b) {//true 已存在
            response.getWriter().write("1");//不可用
            return null;
        }
        response.getWriter().write("0");//可用  数据库无对象
        return null;
    }

    //用户退出功能
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1把session中的信息移除
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //2session失效
        session.invalidate();
        //3删除自动登录的cooke
        Cookie cookie = new Cookie("userInfo", "");
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //4转发重定向
        return "redirect:/index.jsp";
    }

    //新增地址
    public String addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //获取前端数据
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        //非空验证
        if (StringUtils.isEmpty(name)) {
            request.setAttribute("msg", "收货人不能为空");
            return "/address_info.jsp";
        }
        if (StringUtils.isEmpty(phone)) {
            request.setAttribute("msg", "收货人电话不能为空");
            return "/address_info.jsp";
        }
        if (StringUtils.isEmpty(detail)) {
            request.setAttribute("msg", "收货人地址不能为空");
            return "/address_info.jsp";
        }
        //调用业务逻辑
        try {
            AddressService addressService = new AddressServiceImpl();
            Address address = new Address(0, detail, name, phone, user.getId(), 0);
            addressService.add(address);
            return "redirect:/userservlet?method=getAddress";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "添加地址失败：" + e.getMessage());
            return "/address_info.jsp";
        }
    }

    //查询地址
    public String getAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //调用业务逻辑
        try {
            AddressService addressService = new AddressServiceImpl();
            List<Address> addList = addressService.find(user.getId());
            request.setAttribute("addList", addList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/address_info.jsp";
    }

    //删除地址功能
    public String deleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //获取前端数据
        String id = request.getParameter("id");
        //调用业务逻辑
        try {
            AddressService addressService = new AddressServiceImpl();
            addressService.remove(user.getId(), Integer.parseInt(id));
            return "redirect:/userservlet?method=getAddress";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "删除地址失败：" + e.getMessage());
            return "/address_info.jsp";
        }
    }

    //设置默认地址
    public String defaultAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //2获取前数据
        String id = request.getParameter("id");
        //3调用业务逻辑
        try {
            AddressService addressService = new AddressServiceImpl();
            addressService.updateLevel(user.getId(), Integer.parseInt(id));
            return "redirect:/userservlet?method=getAddress";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "设置默认地址失败" + e.getMessage());
            return "/address_info.jsp";
        }
    }

    //更新地址功能
    public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //获取前端数据
        String id = request.getParameter("id");
        String level = request.getParameter("level");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        //非空验证
        if (StringUtils.isEmpty(id)) {
            request.setAttribute("msg", "地址编号不能为空");
            return "/address_info.jsp";
        }
        if (StringUtils.isEmpty(name)) {
            request.setAttribute("msg", "姓名不能为空");
            return "/address_info.jsp";
        }
        if (StringUtils.isEmpty(phone)) {
            request.setAttribute("msg", "电话不能为空");
            return "/address_info.jsp";
        }
        if (StringUtils.isEmpty(detail)) {
            request.setAttribute("msg", "详细地址不能为空");
            return "/address_info.jsp";
        }
        //调用业务逻辑
        try {
            AddressService addressService = new AddressServiceImpl();
            Address address = new Address(Integer.parseInt(id), detail, name, phone, user.getId(), Integer.parseInt(level));
            addressService.update(address);
            return "redirect:/userservlet?method=getAddress";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "更新地址失败：" + e.getMessage());
            return "/address_info.jsp";
        }
    }

    //后台管理登录adminLogin
    public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //非空校验
        if (StringUtils.isEmpty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "/message.jsp";
        }
        if (StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "/message.jsp";
        }
        //调用业务逻辑
        try {
            UserService userService = new UserServiceImpl();
            User user = userService.adminLogin(username, password);
            //把用户存入session中
            request.getSession().setAttribute("user", user);
            //转发重定向
            return "redirect:/admin/admin.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "登录失败" + e.getMessage());
            return "/message.jsp";
        }
    }

    //用户退出功能
    public String adminlogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把session中的信息移除
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //session失效
        session.invalidate();
        //4转发重定向
        return "redirect:/admin/login.jsp";
    }
}
