package com.qf.service;

import com.qf.domain.User;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:08
 * description:
 */
public interface UserService {
    boolean checkUserName(String username);

    void register(User user);

    void active(String email, String code);

    User login(String username, String password);

    User adminLogin(String username, String password);

    List<User> adminfind(Integer id);

    List<User> adminselect();
}
