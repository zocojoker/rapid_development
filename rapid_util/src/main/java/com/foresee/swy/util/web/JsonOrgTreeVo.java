package com.foresee.swy.util.web;

import com.foresee.fbrp.utils.web.JsonTreeVo;

/**
 * 
 * <pre>
 * 对org ZTree进行传递数据的JSON类。
 * </pre>
 * @author zouyongqiao  zouyongqiao@foresee.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class JsonOrgTreeVo extends JsonTreeVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 578985975678848208L;
	
    private String code;	
	private String orgType;	
	private String orgLevel;	

	
	/**
	 * 默认的构造方法。
	 */
	public JsonOrgTreeVo(){
		
	}
	public JsonOrgTreeVo(String id, String pId, String name, boolean open,
			String click,String code,String orgType,String orgLevel){
		super(id, pId, name, open, click);
		this.code = code;
		this.orgType = orgType;
		this.orgLevel = orgLevel;
	}
	

	/**
	 * 获取code。
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置code。
	 * 
	 * @param code String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取orgType。
	 * 
	 * @param String
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * 设置orgType。
	 * 
	 * @param orgTyp String
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	
	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
