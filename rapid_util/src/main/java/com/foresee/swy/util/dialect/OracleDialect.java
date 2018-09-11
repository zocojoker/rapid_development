/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.swy.util.dialect;


import com.foresee.fbrp.utils.InternationalizationUtil;
import com.foresee.fbrp.utils.dialect.Dialect;

/**
 * <pre>
 * 针对Oracle数据库的方言。
 * </pre>
 * 
 * @author luoshifei luoshifei@foresee.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class OracleDialect extends Dialect {

   /**
    *  获得分页语句。
    *  
    * @param sql  String
    * @param offset int
    * @param length int
    * 
    * @return String
    */
	public String getPagedString(String sql, int offset, int length) {
		int end = offset + length;
		sql = sql.trim();
		String forUpdateClause = null;
		boolean isForUpdate = false;
		final int forUpdateIndex = InternationalizationUtil.toLowerCase(sql).lastIndexOf("for update");
		if (forUpdateIndex > -1) {
			forUpdateClause = sql.substring(forUpdateIndex);
			sql = sql.substring(0, forUpdateIndex - 1);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= ").append(end).append(") where rownum_ > ").append(offset);

		if (isForUpdate) {
			pagingSelect.append(" ");
			pagingSelect.append(forUpdateClause);
		}

		return pagingSelect.toString();

	}

	/**
	 * 是否支持分页。
	 * 
	 * @return boolean
	 */
	public boolean supportsPaged() {
		return true;
	}

}
