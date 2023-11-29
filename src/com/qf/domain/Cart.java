package com.qf.domain;

import java.math.BigDecimal;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:43
 * description:购物车类
 */
public class Cart {
    private Integer id;
    private Integer pid;
    private Integer num;
    private BigDecimal money;
    //添加商品信息
    private Goods goods;

    public Cart() {
    }

    public Cart(Integer id, Integer pid, Integer num, BigDecimal money) {
        this.id = id;
        this.pid = pid;
        this.num = num;
        this.money = money;
    }

    public Cart(Integer id, Integer pid, Integer num, BigDecimal money, Goods goods) {
        this.id = id;
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
        return "Cart{id = " + id + ", pid = " + pid + ", num = " + num + ", money = " + money + ", goods = " + goods + "}";
    }
}
