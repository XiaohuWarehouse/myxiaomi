package com.qf.dao.impl;

import com.qf.dao.GoodsDao;
import com.qf.domain.Goods;
import com.qf.utils.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 16:34
 * description:
 */
public class GoodsDaoImpl implements GoodsDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());
    @Override
    public long getCount(String where, List<Object> params) {
        String sql = " SELECT COUNT(*) FROM `tb_goods`  " + where;
        try {
            return queryRunner.query(sql, new ScalarHandler<>(), params.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> selectByPage(int page_num, int page_size, String where, List<Object> params) {
        params.add((page_num - 1) * page_size);
        params.add(page_size);
        String sql = " SELECT * FROM `tb_goods` " + where + " ORDER BY id LIMIT ?,? ";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Goods.class), params.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods selectById(Integer gid) {
        String sql = " SELECT * FROM `tb_goods` WHERE id=? ";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Goods.class), gid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
