package com.qf.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:45
 * description:商品类
 */
public class Goods {
    private Integer id;
    private String name;
    private Date pubdate;
    private String picture;
    private BigDecimal price;
    private Integer star;
    private String intro;
    private Integer typeid;
    private GoodsType goodsType;

    public Goods() {
    }

    public Goods(Integer id, String name, Date pubdate, String picture, BigDecimal price, Integer star, String intro, Integer typeid) {
        this.id = id;
        this.name = name;
        this.pubdate = pubdate;
        this.picture = picture;
        this.price = price;
        this.star = star;
        this.intro = intro;
        this.typeid = typeid;
    }

    public Goods(Integer id, String name, Date pubdate, String picture, BigDecimal price, Integer star, String intro, Integer typeid, GoodsType goodsType) {
        this.id = id;
        this.name = name;
        this.pubdate = pubdate;
        this.picture = picture;
        this.price = price;
        this.star = star;
        this.intro = intro;
        this.typeid = typeid;
        this.goodsType = goodsType;
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
     * @return pubdate
     */
    public Date getPubdate() {
        return pubdate;
    }

    /**
     * 设置
     * @param pubdate
     */
    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    /**
     * 获取
     * @return picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取
     * @return star
     */
    public Integer getStar() {
        return star;
    }

    /**
     * 设置
     * @param star
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * 获取
     * @return intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置
     * @param intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取
     * @return typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 设置
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取
     * @return goodsType
     */
    public GoodsType getGoodsType() {
        return goodsType;
    }

    /**
     * 设置
     * @param goodsType
     */
    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String toString() {
        return "Goods{id = " + id + ", name = " + name + ", pubdate = " + pubdate + ", picture = " + picture + ", price = " + price + ", star = " + star + ", intro = " + intro + ", typeid = " + typeid + ", goodsType = " + goodsType + "}";
    }
}
