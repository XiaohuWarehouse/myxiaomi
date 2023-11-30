package com.qf.service.impl;

import com.qf.dao.GoodsDao;
import com.qf.dao.impl.GoodsDaoImpl;
import com.qf.domain.Goods;
import com.qf.domain.GoodsType;
import com.qf.domain.PageBean;
import com.qf.service.GoodsService;
import com.qf.service.GoodsTypeService;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 16:29
 * description:
 */
public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public PageBean<Goods> findByPage(int page_num, int page_size, String where, List<Object> params) {
        long totalSize = goodsDao.getCount(where,params);
        List<Goods> data = goodsDao.selectByPage(page_num,page_size,where,params);
        PageBean<Goods> pageBean = new PageBean<Goods>(page_num,page_size,totalSize,data);
        return pageBean;
    }

    @Override
    public Goods findById(Integer gid) {
        Goods goods = goodsDao.selectById(gid);
        if (goods != null) {
            Integer typeid = goods.getTypeid();
            GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
            GoodsType goodsType = goodsTypeService.findById(typeid);
            goods.setGoodsType(goodsType);
        }
        return goods;
    }

    @Override
    public void add(Goods goods) {
        goodsDao.insert(goods);
    }
}















