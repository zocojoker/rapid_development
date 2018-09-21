package com.zoco.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zoco.entity.HandleRecord;
import com.zoco.exception.RapidException;
import com.zoco.service.IHandleRecordService;
import com.zoco.vo.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 操作记录日志公用方法 2017年10月16日
 * 
 * @author zoco zhuochao@zoco.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class LogHandleRecord {
	@Autowired
	private IHandleRecordService hrService;

	/**
	 * 记录操作日志
	 * 
	 * @param request
	 * @param czlx
	 * @param obj(操作的对象)
	 * @return 返回-1，请求信息request为空；返回-2，没有获取到用户信息；返回-3，未知操作类型；返回0，成功；
	 * @throws ParseException
	 */
	public int RecorddLog(HttpServletRequest request, String czlx, String message) throws ParseException {
		HandleRecord handleRecord = new HandleRecord();
		UserLogin user = null;
		String ip = null;
		if (request != null) {
			// 获取账户信息
			HttpSession session = request.getSession();
			user = (UserLogin) session.getAttribute("userLogin");
			// 获取请求客户端Ip信息
			ip = request.getRemoteAddr();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String czsj = sdf.format(new Date());
			// 填充信息
			handleRecord.setRzId(RandNumder.getGuid());
			if (user != null) {
				handleRecord.setZhId(user.getZhId());
				handleRecord.setDlzh(user.getDlzh());
				handleRecord.setIp(ip);
				handleRecord.setCzsj(sdf.parse(czsj));
				handleRecord.setCzlx(czlx);
				if ("1".equals(czlx)) {
					handleRecord.setCznr(message);
				} else if ("2".equals(czlx)) {
					handleRecord.setCznr(message);
				} else if ("3".equals(czlx)) {
					handleRecord.setCznr(message);
				} else if ("4".equals(czlx)) {
					handleRecord.setCznr(message);
				} else if ("5".equals(czlx)) {
					handleRecord.setCznr(message);
				} else {
					return -3;
				}
			} else {
				return -2;
			}
		} else {
			return -1;
		}
		try {
			hrService.insertHandleRecord(handleRecord);
			return 0;
		} catch (Exception e) {
			throw new RapidException(e);
		}
	}
}
