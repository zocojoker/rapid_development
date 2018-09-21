package com.zoco.fbrp.utils.dialect;

public abstract class Dialect {

	public abstract String getPagedString(String query, int offset, int limit);

}