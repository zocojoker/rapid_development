package com.foresee.dao;

import java.util.List;


import com.foresee.entity.HandleRecord;
import com.foresee.swy.util.PagedResultEntity;

/**
 * 日志操作记录dao层接口 2017年10月16日
 * 
 * @author foresee zhuochao@foresee.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public interface IHandleRecordDao {
	/**
	 * 获取所有的日志操作记录
	 * 
	 * @return
	 */
	List<IHandleRecordDao> getAllHandleRecords();

	/**
	 * 指定日志ID获取对应的日志操作记录
	 * 
	 * @param rzId
	 * @return
	 */
	HandleRecord getHandleRecordByRzId(String rzId);

	/**
	 * 获取记录总数
	 * 
	 * @return
	 */
	long getTotalCount();

	/**
	 * 获取所有日志操作记录的分页数据
	 * 
	 * @param page
	 * @return
	 */
	PagedResultEntity getPageHandleRecord(PagedResultEntity<HandleRecord> page);

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
