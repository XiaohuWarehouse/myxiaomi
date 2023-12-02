package com.qf.dao.impl;

import com.qf.dao.OrderDao;
import com.qf.domain.Goods;
import com.qf.domain.Order;
import com.qf.domain.OrderDetail;
import com.qf.domain.User;
import com.qf.utils.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/29 14:28
 * description:
 */
public class OrderDaoImpl implements OrderDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public void add(Order order) {
        Connection connection = DataSourceUtil.getConnection();
        String sql = " INSERT INTO `tb_order` (`id`,`uid`,`money`,`status`,`time`,`aid`) VALUES (?,?,?,?,?,?) ";
        Object[] params = {order.getId(), order.getUid(), order.getMoney(), order.getStatus(), order.getTime(), order.getAid()};
        try {
            queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtil.closeAll(null, null, connection);
        }
    }

    @Override
    public void addDetail(OrderDetail orderDetail) {
        Connection connection = DataSourceUtil.getConnection();
        String sql = " INSERT INTO `tb_orderdetail` (`oid`,`pid`,`num`,`money`) VALUES (?,?,?,?) ";
        Object[] params = {orderDetail.getOid(), orderDetail.getPid(), orderDetail.getNum(), orderDetail.getMoney()};
        try {
            queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtil.closeAll(null, null, connection);
        }
    }

    @Override
    public List<Order> select(Integer uid) {
        Connection connection = DataSourceUtil.getConnection();
        String sql = " SELECT * FROM `tb_order` WHERE `uid`=? ";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(Order.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtil.closeAll(null, null, connection);
        }
    }

    @Override
    public Order selectByOid(String oid) {
        Connection connection = DataSourceUtil.getConnection();
        String sql = " SELECT * FROM `tb_order` WHERE `id`=?  ";
        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(Order.class), oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtil.closeAll(null, null, connection);
        }
    }

    @Override
    public List<OrderDetail> selectDetail(String oid) {
        Connection connection = DataSourceUtil.getConnection();
        String sql = " SELECT * FROM `tb_orderdetail` WHERE `oid`=? ";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(OrderDetail.class), oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtil.closeAll(null, null, connection);
        }
    }

    @Override
    public List<Order> selectByPage(String string, List<Object> params) {
        String sql = "SELECT * FROM `tb_order` " + string;
        try {
            Connection connection = DataSourceUtil.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(Order.class), params.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
