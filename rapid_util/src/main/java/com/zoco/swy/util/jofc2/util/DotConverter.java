/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.swy.util.jofc2.util;


import com.zoco.swy.util.jofc2.model.elements.LineChart.Dot;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.path.PathTrackingWriter;

/**
 * 
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author linjunxiong  linjunxiong@zoco.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class DotConverter extends ConverterBase<Dot> {

	@Override
	public void convert(Dot o, PathTrackingWriter writer, MarshallingContext mc) {
		writeNode(writer, "value", o.getValue(), false);
		writeNode(writer, "colour", o.getColour(), true);
		writeNode(writer, "dot-size", o.getDotSize(), true);
		writeNode(writer, "halo-size", o.getHaloSize(), true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean canConvert(Class arg0) {
		return Dot.class.isAssignableFrom(arg0);
	}

}
