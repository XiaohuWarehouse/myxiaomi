package com.qf.domain;

import java.math.BigDecimal;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:52
 * description:订单详情类
 */
public class OrderDetail {
    private Integer id;
    private String oid;
    private Integer pid;
    private Integer num;
    private BigDecimal money;

    //管理商品表
    private Goods goods;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, String oid, Integer pid, Integer num, BigDecimal money) {
        this.id = id;
        this.oid = oid;
        this.pid = pid;
        this.num = num;
        this.money = money;
    }

    public OrderDetail(Integer id, String oid, Integer pid, Integer num, BigDecimal money, Goods goods) {
        this.id = id;
        this.oid = oid;
        this.pid = pid;
        this.num = num;
        this.money = money;
        this.goods = goods;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return oid
     */
    public String getOid() {
        return oid;
    }

    /**
     * 设置
     * @param oid
     */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * 获取
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取
     * @return money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取
     * @return goods
     */
    public Goods getGoods() {
        return goods;
    }

    /**
     * 设置
     * @param goods
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String toString() {
        return "OrderDetail{id = " + id + ", oid = " + oid + ", pid = " + pid + ", num = " + num + ", money = " + money + ", goods = " + goods + "}";
    }
}
