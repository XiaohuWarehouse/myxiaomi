package com.qf.dao;

import com.qf.domain.Goods;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 16:34
 * description:
 */
public interface GoodsDao {
    long getCount(String where, List<Object> params);

    List<Goods> selectByPage(int page_num, int page_size, String where, List<Object> params);

    Goods selectById(Integer gid);
}
