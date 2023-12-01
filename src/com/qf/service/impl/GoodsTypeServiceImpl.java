package com.qf.service.impl;

import com.qf.dao.GoodsTypeDao;
import com.qf.dao.impl.GoodsTypeDaoImpl;
import com.qf.domain.GoodsType;
import com.qf.service.GoodsTypeService;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 11:42
 * description:
 */
public class GoodsTypeServiceImpl implements GoodsTypeService {
    private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public List<GoodsType> findByLevel(int level) {
        return goodsTypeDao.select(level);
    }

    @Override
    public GoodsType findById(Integer typeId) {
        return goodsTypeDao.selectById(typeId);
    }

    @Override
    public List<GoodsType> find() {
        return goodsTypeDao.adminselect();
    }
}
