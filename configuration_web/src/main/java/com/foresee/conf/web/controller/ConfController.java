
/**
 *
 */
package com.foresee.conf.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foresee.conf.constants.ConfigurationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project web-center-com.foresee.conf
 * @copyright Copyright (c) 2016 foresee
 * @company foresee
 * @author Tim
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Controller
@Slf4j
public class ConfController {

	private Map<String, List<Map<String, Object>>> dbPropertyValues = new HashMap<String, List<Map<String, Object>>>();
	private Map<String,Long> dbPropertyTimout = new HashMap<String,Long>();//记录配置表的加载时间
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/**/*")
	public void conf(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String action = request.getParameter("action");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (ConfigurationConstants.ACTION_RELOAD.equals(action)) {// 重载配置文件
			dbPropertyValues.clear();
			dbPropertyTimout.clear();
		}
		String confTable = request.getParameter("confTable");
		if (confTable == null) {
			confTable = ConfigurationConstants.DEFAULT_CONFIGURATION_TABLENAME;
		}
		//如果配置表没加载过或者加载已经超过1分钟了就重新加载
		if (dbPropertyValues.get(confTable) == null || (new Date().getTime()-dbPropertyTimout.get(confTable)>60*1000)) {
			// 从数据库中加载配置项
			String sql = "select * from "+confTable;
			log.debug("准备读取配置表："+confTable);
			List<Map<String, Object>> conf = this.jdbcTemplate.queryForList(sql);
			log.debug("读取配置文件表（"+confTable+"）成功！");
			dbPropertyValues.put(confTable, conf);
			dbPropertyTimout.put(confTable, new Date().getTime());//记录配置表的加载时间
		}
		if (ConfigurationConstants.ACTION_RELOAD.equals(action) || ConfigurationConstants.ACTION_VIEW.equals(action)) {
			String html = "<table width='100%' border='0' cellspacing='0' cellpadding='0' style='border-top:solid 1px gray;border-left:solid 1px gray;'><thead style='background-color:lightblue;'><tr><th style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>模块</th><th style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>配置项名称</th><th style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>配置项值</th><th style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>描述</th></tr></thrad><tbody>";
			for (Map<String, Object> conf : dbPropertyValues.get(confTable)) {
				html += "<tr><td style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>"
						+ conf.get("module")
						+ "</td><td style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>"
						+ conf.get("key")
						+ "</td><td style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>"
						+ conf.get("value")
						+ "</td><td style='padding:5px;border-right:solid 1px gray;border-bottom:solid 1px gray;'>"
						+ conf.get("desc") + "</td></tr>";
			}
			html += "</tbody></table>";
			response.getWriter().write(html);
		} else {
			Map<String, Object> confMap = new HashMap<String, Object>();
			for (Map<String, Object> conf : dbPropertyValues.get(confTable)) {
				confMap.put(conf.get("module") + "." + conf.get("key"), conf.get("value"));
			}
			response.getWriter().write(new ObjectMapper().writeValueAsString(confMap));
		}
	}
	
	@RequestMapping(value="/**/*",params="action=confTableEdit")
	public String confTableEdit(HttpServletRequest request, HttpServletResponse response){
		return "confTableEdit";
	}
	
	@RequestMapping(value="/**/*",params="action=edit")
	public @ResponseBody Map<String,String> edit(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> ret = new HashMap<String,String>();
		String confTable = request.getParameter("confTable");
		String module = request.getParameter("module");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		String desc = request.getParameter("desc");
		String sql = "update "+confTable+" set `value`=?,`desc`=? where module=? and `key`=?";
		this.jdbcTemplate.update(sql, value,desc,module,key);
		ret.put("flag", "ok");
		return ret;
	}
	
	@RequestMapping(value="/**/*",params="action=add")
	public @ResponseBody Map<String,String> add(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> ret = new HashMap<String,String>();
		String confTable = request.getParameter("confTable");
		String module = request.getParameter("module");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		String desc = request.getParameter("desc");
		String sql = "insert into "+confTable+" (module,`key`,`value`,`desc`) values (?,?,?,?)";
		this.jdbcTemplate.update(sql, module,key,value,desc);
		ret.put("flag", "ok");
		return ret;
	}
	
	@RequestMapping(value="/**/*",params="action=del")
	public @ResponseBody Map<String,String> del(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> ret = new HashMap<String,String>();
		String confTable = request.getParameter("confTable");
		String module = request.getParameter("module");
		String key = request.getParameter("key");
		String sql = "delete from "+confTable+" where module=? and `key`=?";
		this.jdbcTemplate.update(sql, module,key);
		ret.put("flag", "ok");
		return ret;
	}
	
	@RequestMapping(value="/**/*",params="action=publish")
	public @ResponseBody Map<String,String> publish(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> ret = new HashMap<String,String>();
		String confTable = request.getParameter("confTable");
		dbPropertyValues.remove(confTable);
		dbPropertyTimout.remove(confTable);
		//TODO 发jms消息，通知接入方马上刷新配置
		ret.put("flag", "ok");
		return ret;
	}
}
