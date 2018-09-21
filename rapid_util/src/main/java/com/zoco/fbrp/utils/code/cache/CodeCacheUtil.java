/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.fbrp.utils.code.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zoco.fbrp.utils.code.CodeObject;

/**
 * <pre>
 * 代码表缓存工具类，用户数据转换。
 * </pre>
 * 
 * @author lijia lijia@ycs168.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CodeCacheUtil {
	/**
	 * 将代码表缓存的Collection<CodeObject>数据转换成List<CodeObject>
	 */
	public static List<CodeObject> convertList(Collection<CodeObject> collections) {
		List<CodeObject> retList = new ArrayList<CodeObject>();
		for (CodeObject co : collections) {
			CodeObject temp = new CodeObject();
			temp.setName(co.getName());
			temp.setValue(co.getValue());
			retList.add(temp);
		}
		return retList;
	}
}
