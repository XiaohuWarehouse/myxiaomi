package com.qf.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:49
 * description:订单类
 */
public class Order {
    private String id;
    private Integer uid;
    private BigDecimal money;
    private String status;
    private Date time;
    private Integer aid;

    //订单与地址关联
    private Address address;

    public Order() {
    }

    public Order(String id, Integer uid, BigDecimal money, String status, Date time, Integer aid) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.aid = aid;
    }

    public Order(String id, Integer uid, BigDecimal money, String status, Date time, Integer aid, Address address) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.aid = aid;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
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
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取
     * @return aid
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置
     * @param aid
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        return "Order{id = " + id + ", uid = " + uid + ", money = " + money + ", status = " + status + ", time = " + time + ", aid = " + aid + ", address = " + address + "}";
    }
}
