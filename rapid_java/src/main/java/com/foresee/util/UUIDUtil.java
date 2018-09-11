package com.foresee.util;

import java.util.UUID;

/**
 * 
 * 2017年7月21日
 * 
 * @author foresee zhuochao@foresee.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class UUIDUtil {
	/**
	 * 自动生成32位的UUid，对应数据库的主键id进行插入用。
	 * 
	 * @return
	 */
	public static String getUUID() {
		/*
		 * UUID uuid = UUID.randomUUID(); String str = uuid.toString(); //
		 * 去掉"-"符号 String temp = str.substring(0, 8) + str.substring(9, 13) +
		 * str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		 * return temp;
		 */

		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		// String[] ss = getUUID(10);
		for (int i = 0; i < 10; i++) {
			System.out.println("ss[" + i + "]=====" + getUUID());
		}
	}
}
