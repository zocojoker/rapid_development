package com.foresee.service;

import com.foresee.dao.IHandleRecordDao;
import com.foresee.entity.HandleRecord;
import com.foresee.swy.util.PagedResultEntity;

import java.util.List;

/**
 * 日志业务处理
 * author foresee
 * creat time 2018/4/8
 */
public interface IHandleRecordService {
    /**
     * 获取所有的日志操作记录
     *
     * @return
     */
    List<IHandleRecordDao> getAllHandleRecords();

    /**
     *
     * 指定日志ID获取对应的日志操作记录
     *
     * @param rzId
     * @return
     */
    HandleRecord getHandleRecordByRzId(String rzId);

    /**
     * 获取所有日志操作记录的分页数据
     *
     * @return
     */
    PagedResultEntity<HandleRecord> getPageHandleRecord(long pages, long rows);

    /**
     * 新增一条操作记录
     *
     * @param handleRecord
     * @return
     */
    int insertHandleRecord(HandleRecord handleRecord);

    /**
     * 更新一条操作记录
     *
     * @param handleRecord
     * @return
     */
    int updateHandleRecord(HandleRecord handleRecord);

    /**
     * 删除一条操作记录
     *
     * @param rzId
     * @return
     */
    int deleteHandleRecord(String rzId);
}
