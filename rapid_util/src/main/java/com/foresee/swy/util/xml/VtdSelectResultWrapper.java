/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.fbrp.util.xml;

/**
 * <pre>
 * vtd 查询的结果包装类。
 * </pre>
 * @author wangbozheng  wangbozheng@foresee.com.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class VtdSelectResultWrapper {
	private String nodeName;
	private String text;
	private boolean isXmlNode = false; 
	/**
	 * 返回 nodeName。
	 * @return 返回 nodeName。
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * 返回 isXmlNode。
	 * @return 返回 isXmlNode。
	 */
	public boolean isXmlNode() {
		return isXmlNode;
	}
	/**
	 * 返回 isXmlNode。
	 * @param isXmlNode 设置 isXmlNode。
	 */
	public void setXmlNode(boolean isXmlNode) {
		this.isXmlNode = isXmlNode;
	}
	/**
	 * 返回 nodeName。
	 * @param nodeName 设置 nodeName。
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * 返回 text。
	 * @return 返回 text。
	 */
	public String getText() {
		return text;
	}
	/**
	 * 返回 text。
	 * @param text 设置 text。
	 */
	public void setText(String text) {
		this.text = text;
	}
}
