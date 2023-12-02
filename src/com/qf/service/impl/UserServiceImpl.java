package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.domain.User;
import com.qf.service.UserService;
import com.qf.utils.EmailUtils;
import com.qf.utils.MD5Utils;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:09
 * description:
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean checkUserName(String username) {
        User user = userDao.selectByUserName(username);
        if (user != null) {
            return true;//用户已存在 1 不能注册
        }
        return false;//不存在  0  可以注册
    }

    @Override
    public void register(User user) {
        //密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));
        userDao.insert(user);
        //发送邮件
        EmailUtils.sendEmail(user);
    }

    @Override
    public void active(String email, String code) {
        int result = userDao.updateFlag(email, code);
    }

    @Override
    public User login(String username, String password) {
        //加密
        password = MD5Utils.md5(password);
        //查找用户
        User user = userDao.selectByUserName(username);
        //判断是否存在
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        //比对密码
        if (!user.getPassword().equalsIgnoreCase(password)) {
            throw new RuntimeException("密码错误");
        }
        //是否激活
        if (user.getFlag() != 1) {
            throw new RuntimeException("账户未激活或激活已过期");
        }
        return user;
    }

    @Override
    public User adminLogin(String username, String password) {
        //加密
        password = MD5Utils.md5(password);
        //查找用户
        User user = userDao.selectByUserName(username);
        //判断是否存在
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        //比对密码
        if (!user.getPassword().equalsIgnoreCase(password)) {
            throw new RuntimeException("密码错误");
        }
        //是否激活
        if (user.getFlag() != 1) {
            throw new RuntimeException("账户未激活或激活已过期");
        }
        //是否为管理员
        if (user.getRole() != 0) {
            throw new RuntimeException("账户非管理员");
        }
        return user;
    }

    @Override
    public List<User> adminfind(Integer id) {
        return userDao.adminselect(id);
    }

    @Override
    public List<User> adminselect() {
        return userDao.adminselect();
    }

    @Override
    public User selectUserName(String username) {
        return userDao.selectByUserName(username);
    }
}
