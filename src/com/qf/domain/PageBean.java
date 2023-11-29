package com.qf.domain;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 14:33
 * description:分页实体类
 */
public class PageBean<T> {
    private int pageNum;
    private int pageSize;
    private long totalSize;
    private int pageCount;
    private List<T> data;
    private int startPage;
    private int endPage;

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;
        //计算总页数
        this.pageCount = (int) (totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1);
        this.startPage = this.pageNum-4;
        this.endPage = this.pageNum+5;
        if (this.pageNum<=5) {
            this.startPage=1;
            this.endPage=10;
        }
        if (this.pageNum>=this.pageCount-5) {
            this.startPage=this.pageCount-9;
            this.endPage=this.pageCount;
        }
        //当页面不足十页时
        if (this.pageCount<=10) {
            this.startPage=1;
            this.endPage=pageCount;
        }
    }

    public PageBean(int pageNum, int pageSize, long totalSize, int pageCount, List<T> data, int startPage, int endPage) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.pageCount = pageCount;
        this.data = data;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    /**
     * 获取
     * @return pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置
     * @param pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获取
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取
     * @return totalSize
     */
    public long getTotalSize() {
        return totalSize;
    }

    /**
     * 设置
     * @param totalSize
     */
    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * 获取
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 设置
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 获取
     * @return data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 获取
     * @return startPage
     */
    public int getStartPage() {
        return startPage;
    }

    /**
     * 设置
     * @param startPage
     */
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    /**
     * 获取
     * @return endPage
     */
    public int getEndPage() {
        return endPage;
    }

    /**
     * 设置
     * @param endPage
     */
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String toString() {
        return "PageBean{pageNum = " + pageNum + ", pageSize = " + pageSize + ", totalSize = " + totalSize + ", pageCount = " + pageCount + ", data = " + data + ", startPage = " + startPage + ", endPage = " + endPage + "}";
    }
}
