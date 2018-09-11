/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.swy.util.jofc2.util;


import com.foresee.swy.util.InternationalizationUtil;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.foresee.swy.util.jofc2.axis.Label.Rotation;

/**
 * 
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author linjunxiong  linjunxiong@foresee.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class RotationConverter implements SingleValueConverter {

	@Override
	public Object fromString(String s) {
		return Rotation.valueOf(InternationalizationUtil.toUpperCase(s));
	}

	@Override
	public String toString(Object o) {
		return o.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean canConvert(Class c) {
		return Rotation.class.isAssignableFrom(c);
	}

}
