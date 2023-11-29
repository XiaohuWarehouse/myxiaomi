package com.qf.dao.impl;

import com.qf.dao.AddressDao;
import com.qf.domain.Address;
import com.qf.utils.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 19:08
 * description:
 */
public class AddressDaoImpl implements AddressDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    @Override
    public List<Address> select(Integer uid) {
        String sql = " SELECT * FROM `tb_address` WHERE `uid`=? ";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Address.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Address address) {
        try {
            String sql = " INSERT INTO `tb_address` (`detail`,`name`,`phone`,`uid`,`level`) VALUES (?,?,?,?,?) ";
            Object[] params = {address.getDetail(),address.getName(),address.getPhone(),address.getUid(),address.getLevel()};
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer uid, int aid) {
        String sql = " DELETE FROM `tb_address` WHERE `id`=? AND `uid`=? ";
        try {
            queryRunner.update(sql,aid,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLevel(Integer uid, int aid) {
        //当你把一个用户的某一个地址设置为默认地址之后，其他地址为普通地址
        try {
            queryRunner.update(" update tb_address set  level=0 where uid=? ",uid);
            queryRunner.update(" update tb_address set  level=1 where id=? and uid=? ",aid,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Address address) {
        try {
            String sql="  update `tb_address` set `detail`=?,`name`=?,`phone`=?,`level`=? where id=? and `uid`=? ";
            Object[] params={ address.getDetail(),address.getName(),address.getPhone(),address.getLevel(),address.getId(),address.getUid() };
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
