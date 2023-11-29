package com.qf.dao.impl;

import com.qf.dao.UserDao;
import com.qf.domain.User;
import com.qf.utils.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:09
 * description:
 */
public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner=new QueryRunner(DataSourceUtil.getDataSource());
    @Override
    public User selectByUserName(String username) {
        String sql=" select * from tb_user where username=? ";
        try {
            return queryRunner.query(sql,new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User user) {
        String sql=" insert into tb_user values (null,?,?,?,?,?,?,?) ";
        Object[] params={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateFlag(String email, String code) {
        String sql=" update tb_user set flag=1 where email=? and code=? and flag=0 ";
        try {
            return queryRunner.update(sql,email,code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
