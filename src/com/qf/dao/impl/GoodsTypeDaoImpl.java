package com.qf.dao.impl;

import com.qf.dao.GoodsTypeDao;
import com.qf.domain.GoodsType;
import com.qf.utils.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 11:45
 * description:
 */
public class GoodsTypeDaoImpl implements GoodsTypeDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());
    @Override
    public List<GoodsType> select(int level) {
        try {
            String sql = " SELECT `id`,`name`,`level`,`parent` FROM `tb_goods_type` WHERE `level`=? ";
            return queryRunner.query(sql, new BeanListHandler<>(GoodsType.class), level);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GoodsType selectById(Integer typeId) {
        String sql = " SELECT `id`,`name`,`level`,`parent` FROM `tb_goods_type` WHERE id=? ";
        try {
            return queryRunner.query(sql,new BeanHandler<>(GoodsType.class),typeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
