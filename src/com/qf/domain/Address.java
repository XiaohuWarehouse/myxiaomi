package com.qf.domain;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:41
 * description:地址类
 */
public class Address {
    private Integer id;
    private String detail;
    private String name;
    private String phone;
    private Integer uid;
    private Integer level;

    public Address() {
    }

    public Address(Integer id, String detail, String name, String phone, Integer uid, Integer level) {
        this.id = id;
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.uid = uid;
        this.level = level;
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
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    public String toString() {
        return "Address{id = " + id + ", detail = " + detail + ", name = " + name + ", phone = " + phone + ", uid = " + uid + ", level = " + level + "}";
    }
}
