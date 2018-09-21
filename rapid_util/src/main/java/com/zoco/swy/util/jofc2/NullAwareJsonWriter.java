/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.swy.util.jofc2;

import com.thoughtworks.xstream.io.json.JsonWriter;

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
public class NullAwareJsonWriter extends JsonWriter {

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 * @param lineIndenter char[]
	 * @param newLine String
	 * @param mode int
	 */
	public NullAwareJsonWriter(Writer writer, char[] lineIndenter, String newLine, int mode) {
		super(writer, lineIndenter, newLine, mode);
	}

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 * @param lineIndenter char[]
	 * @param newLine String
	 */
	public NullAwareJsonWriter(Writer writer, char[] lineIndenter, String newLine) {
		super(writer, lineIndenter, newLine);
	}

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 * @param lineIndenter char[]
	 */
	public NullAwareJsonWriter(Writer writer, char[] lineIndenter) {
		super(writer, lineIndenter);
	}

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 * @param mode int
	 */
	public NullAwareJsonWriter(Writer writer, int mode) {
		super(writer, mode);
	}

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 * @param lineIndenter String
	 * @param newLine String
	 */
	public NullAwareJsonWriter(Writer writer, String lineIndenter, String newLine) {
		super(writer, lineIndenter, newLine);
	}

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 * @param lineIndenter String
	 */
	public NullAwareJsonWriter(Writer writer, String lineIndenter) {
		super(writer, lineIndenter);
	}

	/**
	 * 构建器。
	 * 
	 * @param writer Writer
	 */
	public NullAwareJsonWriter(Writer writer) {
		super(writer);
	}

}
