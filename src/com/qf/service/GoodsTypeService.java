package com.qf.service;

import com.qf.domain.GoodsType;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 11:42
 * description:
 */
public interface GoodsTypeService {
    List<GoodsType> findByLevel(int level);

    GoodsType findById(Integer typeId);

    List<GoodsType> find();
}
