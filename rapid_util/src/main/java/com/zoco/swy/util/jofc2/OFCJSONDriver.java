/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.swy.util.jofc2;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import java.io.Writer;

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
public class OFCJSONDriver extends JsonHierarchicalStreamDriver {

	@Override
	public HierarchicalStreamWriter createWriter(Writer out) {
		return new NullAwareJsonWriter(out);
	}
}
