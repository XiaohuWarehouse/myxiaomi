package com.qf.dao;

import com.qf.domain.GoodsType;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 11:44
 * description:
 */
public interface GoodsTypeDao {
    List<GoodsType> select(int level);

    GoodsType selectById(Integer typeId);

    List<GoodsType> adminselect();

    void adminadd(GoodsType goodsType);
}
