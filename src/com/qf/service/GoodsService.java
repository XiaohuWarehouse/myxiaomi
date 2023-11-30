package com.qf.service;

import com.qf.domain.Goods;
import com.qf.domain.PageBean;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 16:28
 * description:
 */
public interface GoodsService {
    PageBean<Goods> findByPage(int page_num, int page_size, String where, List<Object> params);

    Goods findById(Integer gid);

    void add(Goods goods);
}
