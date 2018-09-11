package com.foresee.fbrp.utils.dialect;

public class MySQLDialect extends Dialect {

	public String getPagedString(String sql, int offset, int length) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		sqlBuffer.append(" limit ");
		sqlBuffer.append(offset);
		sqlBuffer.append(", ");
		sqlBuffer.append(length);
		return sqlBuffer.toString();
	}
}
