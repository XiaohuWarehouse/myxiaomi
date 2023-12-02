package com.qf.dao;

import com.qf.domain.User;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:09
 * description:
 */
public interface UserDao {
    User selectByUserName(String username);

    void insert(User user);

    int updateFlag(String email, String code);

    List<User> adminselect(Integer id);

    List<User> adminselect();

    List<User> adminDeleteuser(String id);
}
