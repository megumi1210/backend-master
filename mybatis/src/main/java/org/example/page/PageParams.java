package org.example.page;

import org.apache.ibatis.plugin.Intercepts;

import java.net.Inet4Address;

/**
 * @author chenj
 */
public class PageParams {
    /**
     *  当前页码
     */
    private Integer currentPage;
    /**
     * 每页限制条数
     */
    private Integer pageSize;
    /**
     *  是否启用插件，如果不启动则不作分页
     */
    private Boolean useFlag;
    /**
     * 是否检测页码的有效性，如果为true,而页码大于页数，则抛出异常
     */
    private Boolean checkFlag;

    /**
     * 是否清除order by 后面的语句
     */
    private Boolean cleanOrderBy;

    /**
     * 总条数，插件会回填这个值
     */
    private Integer total;
    /**
     * 总页数，插件会回填这个值
     */
    private Integer totalPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Boolean getCleanOrderBy() {
        return cleanOrderBy;
    }

    public void setCleanOrderBy(Boolean cleanOrderBy) {
        this.cleanOrderBy = cleanOrderBy;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
