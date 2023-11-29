package com.qf.domain;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:48
 * description:商品类别
 */
public class GoodsType {
    private Integer id;
    private String name;
    private Integer level;
    private Integer parent;

    public GoodsType() {
    }

    public GoodsType(Integer id, String name, Integer level, Integer parent) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parent = parent;
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

    /**
     * 获取
     * @return parent
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * 设置
     * @param parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String toString() {
        return "GoodsType{id = " + id + ", name = " + name + ", level = " + level + ", parent = " + parent + "}";
    }
}
