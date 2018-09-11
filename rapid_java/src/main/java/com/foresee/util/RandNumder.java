package com.foresee.util;

import java.text.SimpleDateFormat;

/**
 * 获取19位随机数 4位年份+13位时间戳+3位随机数 2017年10月18日
 * 
 * @author foresee zhuochao@foresee.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class RandNumder {

	public static void main(String[] args) {
		// 调用生成id方法
		System.out.println(getGuid());
	}

	/**
	 * 获取20位长度的数字id
	 * 
	 * @return
	 */
	public static int Guid = 100;

	public static String getGuid() {
		// Common.Guid+=1;
		long now = System.currentTimeMillis();
		// 获取4位年份数字
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		// 获取时间戳
		String time = dateFormat.format(now);
		String info = now + "";
		// 获取三位随机数
		int ran = (int) ((Math.random() * 9 + 1) * 100);
		// 要是一段时间内的数据连过大会有重复的情况，所以做以下修改
		// int ran=0;
		// if(Common.Guid>999){
		// Common.Guid=100;
		// }
		// ran=Common.Guid;
		return time + info.substring(1, info.length()) + ran;
	}
}
