package com.foresee.swy.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DatabaseUtil {
	public static String toJson(Connection conn, PreparedStatement ppst,
			ResultSet rs) {
		JSONArray array = new JSONArray();
		try {
			if (rs == null) {
				return "ResultSet is null";
			}
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnLabel(i);
					String value = rs.getString(columnName);
					jsonObj.put(columnName, value);
				}
				array.add(jsonObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(conn,ppst,rs);
		}
		if (array != null && array.size() > 0) {
			return array.toJSONString();
		}
		return "toJson is error";
	}

	public static void closeConnection(Connection conn, PreparedStatement ppst,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ppst != null) {
				ppst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
