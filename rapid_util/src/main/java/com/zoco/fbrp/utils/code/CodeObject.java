package com.zoco.fbrp.utils.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <pre>
 * 简单键值对数据的封装对象.
 * </pre>
 * 
 * @author bieber hebibo@zoco.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CodeObject {
	private String name;
	private String value;
	private boolean enable = true;
	private String description;
	private CodeObject parent;
	private List<CodeObject> children;
	private LinkedHashMap<String, Object> properties = null;

	/**
	 * 返回 name.
	 * 
	 * @return name。
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name.
	 * 
	 * @param name
	 *            name。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 value.
	 * 
	 * @return value。
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置 value.
	 * 
	 * @param value
	 *            value。
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 返回 description.
	 * 
	 * @return description。
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 description.
	 * 
	 * @param description
	 *            description。
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回 parent.
	 * 
	 * @return parent。
	 */
	public CodeObject getParent() {
		return parent;
	}

	/**
	 * 设置 parent.
	 * 
	 * @param parent
	 *            parent。
	 */
	public void setParent(CodeObject parent) {
		this.parent = parent;
	}

	/**
	 * 返回 children.
	 * 
	 * @return children。
	 */
	public List<CodeObject> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<CodeObject>();
		}
		return children;
	}

	/**
	 * 设置 children.
	 * 
	 * @param children
	 *            children。
	 */
	public void setChildren(List<CodeObject> children) {
		this.children = children;
	}

	/**
	 * 返回 enable.
	 * 
	 * @return enable。
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * 设置 enable.
	 * 
	 * @param enable
	 *            enable。
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * 设置属性.
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void put(String key, Object value) {
		if (this.properties == null) {
			this.properties = new LinkedHashMap<String, Object>();
		}
		this.properties.put(key, value);
	}

	/**
	 * 获取属性的值.
	 * 
	 * @param key
	 *            key
	 * @return value
	 */
	public Object get(String key) {
		if (this.properties != null) {
			return this.properties.get(key);
		}
		return null;
	}
}
