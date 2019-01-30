package com.life.game.utils;

import java.util.List;

/**
 * 分页查询结果工具类
 * @author Wagic
 */
public class PageUtil<T> {
    //具体分页所用的对象组成的集合
    private List<T> list;
    //可分的最大页数
    private int maxPages;
    //总记录条数
    private int totalRecords;
    //每页显示的记录条数
    private int pageRecords = 5;
    //要查询的页码
    private int pageIndex;
    //上一页的页码
    private int lastPageIndex;
    //下一页的页码
    private int nextPageIndex;

    /**
     * 当前页码
     *
     * @return
     */
    public int getPageIndex() {
        return this.pageIndex;
    }

    /**
     * 当前页码
     *
     * @return
     */
    public void setPageIndex(int pageIndex) {
        if (pageIndex <= 1) {
            this.pageIndex = 1;
            return;
        } else if (pageIndex > this.maxPages) {
            this.pageIndex = this.maxPages;
            return;
        }
        this.pageIndex = pageIndex;
    }

    /**
     * 每页记录条数
     *
     * @return
     */
    public int getPageRecords() {
        return pageRecords;
    }

    /**
     * 每页记录条数
     *
     * @return
     */
    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
    }

    /**
     * 每页的记录数据
     *
     * @return
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 每页的记录数据
     *
     * @return
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 总页数
     *
     * @return
     */
    public int getMaxPages() {
        setMaxPages();
        return maxPages;
    }

    /**
     * 总页数
     *
     * @return
     */
    public void setMaxPages() {
        if (totalRecords % pageRecords == 0) {
            maxPages = totalRecords / pageRecords;
        } else {
            maxPages = totalRecords / pageRecords + 1;
        }
    }

    /**
     * 总记录条数
     *
     * @return
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * 总记录条数
     *
     * @return
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * 得到上一页码
     */
    public int getLastPageIndex() {
        pageIndex = getPageIndex(); // 得到当前页码
        if (pageIndex <= 1) {
            return 1;
        }
        return pageIndex - 1;
    }

    public void setLastPageIndex() {
        lastPageIndex = getLastPageIndex();
    }

    /**
     * 得到下一页码
     */
    public int getNextPageIndex() {
        pageIndex = getPageIndex(); // 得到当前页码
        maxPages = getMaxPages(); // 得到最大页码数
        if (pageIndex >= maxPages) {
            return maxPages;
        }
        return pageIndex + 1;
    }

    public void setNextPageIndex() {
        nextPageIndex = getNextPageIndex();
    }
}
