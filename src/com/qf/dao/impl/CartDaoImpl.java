package com.qf.dao.impl;

import com.qf.dao.CartDao;
import com.qf.domain.Cart;
import com.qf.utils.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 10:22
 * description:
 */
public class CartDaoImpl implements CartDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    @Override
    public void insert(Cart cart) {
        String sql = " INSERT INTO `tb_cart` (`id`,`pid`,`num`,`money`)  VALUES  (?,?,?,?)  ";
        Object[] params = {cart.getId(), cart.getPid(), cart.getNum(), cart.getMoney()};
        try {
            queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cart select(Integer uid, int goods_id) {
        String sql = " SELECT * FROM `tb_cart` WHERE id=? AND pid=?  ";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Cart.class), uid, goods_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Cart cart) {
        String sql = " UPDATE `tb_cart` SET `num`=?,`money`=? WHERE id=? AND `pid`=?  ";
        Object[] params = {cart.getNum(), cart.getMoney(), cart.getId(), cart.getPid()};
        try {
            queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> select(Integer uid) {
        String sql = " SELECT * FROM `tb_cart` WHERE id=?   ";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Cart.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer uid, int goods_id) {
        String sql = " DELETE FROM `tb_cart` WHERE `id`=? AND `pid`=? ";
        try {
            queryRunner.update(sql, uid, goods_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll(Integer uid) {
        QueryRunner queryRunner1 = new QueryRunner();
        Connection connection = DataSourceUtil.getConnection();
        String sql = " DELETE FROM `tb_cart` WHERE `id`=? ";
        try {
            queryRunner.update(sql, uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtil.closeAll(null,null,connection);
        }
    }
}
