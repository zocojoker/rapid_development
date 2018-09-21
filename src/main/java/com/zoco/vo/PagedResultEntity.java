/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.zoco.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 分页查询结果。
 * </pre>
 *
 * @param <T>
 *            ValueObject
 * @author wuchunlin wuchunlin@zoco.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class PagedResultEntity<T> implements Serializable {

    private static final long serialVersionUID = -5013970559448931005L;

    /**
     * 数据集合
     */
    private List<T> dataList;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 当前页
     */
    private long page;
    /**
     * 每页显示行数
     */
    private long rows;
    /**
     * 总分页数
     */
    private long totalpages;
    /**
     * 开始行
     */
    private int start;
    /**
     * 结束行
     */
    private int limit;

    /**
     * 默认的构造方法。
     */
    public PagedResultEntity() {
    }

    /**
     * 自定义的构造方法。
     * @param totalCount
     * @param page
     * @param rows
     */
    public PagedResultEntity(long totalCount, long page, long rows) {
        this.totalCount = totalCount;
        this.rows = rows;

        // 计算总分页数
        if (totalCount % rows == 0) {
            this.totalpages = totalCount / rows;
        } else {
            this.totalpages = totalCount / rows + 1;
        }

        // 设置当前页
        if (page <= 0) {
            page = 1;
        }
        if (page >= this.totalpages) {
            page = this.totalpages;
        }
        this.page = page;

        if (this.page > 0) {
            this.start = (int) ((this.page - 1) * this.rows);
            this.limit = (int) (this.page * this.rows);
        } else {
            this.start = 0;
            this.limit = 0;
        }
    }

    /**
     * 构造MAP返回
     *
     * @return
     */
    public Map<String, Object> getReturnMap() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("dataList", this.dataList);
        returnMap.put("page", this.page);
        returnMap.put("totalpages", this.totalpages);
        returnMap.put("totalCount", this.totalCount);
        return returnMap;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public long getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(long totalpages) {
        this.totalpages = totalpages;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PagedResultEntity [dataList=" + dataList + ", totalCount=" + totalCount + ", page=" + page + ", rows="
                + rows + ", totalpages=" + totalpages + ", start=" + start + ", limit=" + limit + "]";
    }
}
