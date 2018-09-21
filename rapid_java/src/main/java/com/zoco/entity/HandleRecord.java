package com.zoco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 2017年10月16日 日志操作记录实体类
 * 
 * @author zoco zhuochao@zoco.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class HandleRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rzId;
	private String zhId;
	private String dlzh;
	private String ip;
	/**
	 * 操作类型
	 */
	private String czlx;
	/**
	 * 操作内容
	 */
	private String cznr;
	private Date czsj;

	public String getRzId() {
		return rzId;
	}

	public void setRzId(String rzId) {
		this.rzId = rzId;
	}

	public String getZhId() {
		return zhId;
	}

	public void setZhId(String zhId) {
		this.zhId = zhId;
	}

	public String getDlzh() {
		return dlzh;
	}

	public void setDlzh(String dlzh) {
		this.dlzh = dlzh;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getCznr() {
		return cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public HandleRecord(String rzId, String zhId, String dlzh, String ip, String czlx, String cznr, Date czsj) {
		super();
		this.rzId = rzId;
		this.zhId = zhId;
		this.dlzh = dlzh;
		this.ip = ip;
		this.czlx = czlx;
		this.cznr = cznr;
		this.czsj = czsj;
	}

	@Override
	public String toString() {
		return "HandleRecord [rzId=" + rzId + ", zhId=" + zhId + ", dlzh=" + dlzh + ", ip=" + ip + ", czlx=" + czlx
				+ ", cznr=" + cznr + ", czsj=" + czsj + "]";
	}

	public HandleRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

}
