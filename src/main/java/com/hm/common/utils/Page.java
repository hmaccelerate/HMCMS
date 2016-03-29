package com.hm.common.utils;

import com.hm.common.config.Config;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
public class Page<T> {
    //配置参数
    private int currentPage;
    private int pageSize;
    //查询数据库
    private int recordCount;
    private List<T> recordList;
    //计算得出
    private int pageCount;
    private int beginIndex;
    private int endIndex;

    public Page() {

    }

    /**
     * @param currentPage 显示第几页
     * @param pageSize    一页显示几条
     * @param recordCount 总共多少记录
     * @param recordList  一页显示的数据
     */
    public Page(int currentPage, int pageSize, int recordCount, List<T> recordList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;

        pageCount = (recordCount + pageSize - 1) / pageSize;

        if (pageCount <= Config.PAGE_NAVIGATE) {
            beginIndex = 1;
            endIndex = pageCount;
        } else if (currentPage <= Config.PAGE_NAVIGATE / 2) {
            beginIndex = 1;
            endIndex = Config.PAGE_NAVIGATE;
        } else if (currentPage + Config.PAGE_NAVIGATE / 2 > pageCount) {
            beginIndex = pageCount - (Config.PAGE_NAVIGATE - 1);
            endIndex = pageCount;
        } else {
            beginIndex = currentPage - Config.PAGE_NAVIGATE / 2;
            endIndex = currentPage + Config.PAGE_NAVIGATE / 2;
        }

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }
}
