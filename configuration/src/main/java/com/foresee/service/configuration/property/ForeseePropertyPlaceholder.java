package com.foresee.service.configuration.property;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.StringUtils;

import com.foresee.service.configuration.constants.ConfigurationConstants;
import com.foresee.service.configuration.utils.HttpClientConnectionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * @project configuration
 * @description 重写PropertyPlaceholderConfigurer的resolvePlaceholder方法，
 * 当从指定的Properties文件中读取不到配置项时，尝试从配置中心读取所有配置信息。
 * @copyright Copyright (c) 2016 foresee
 * @company foresee
 * @author  Tim
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Slf4j
public class ForeseePropertyPlaceholder extends PropertyPlaceholderConfigurer {
	
	private static Map<String,String> dbPropertyValues = null;
	
	public ForeseePropertyPlaceholder(){
		String delay = getDbProperty("com.foresee.conf.refresh.delay");
		int de = 60;
		if(delay == null){
			log.info("配置中心没有设置conf.refresh.delay参数，使用默认值：60。");
		}else{
			de = Integer.parseInt(delay);
		}
		//每分钟执行一次刷新配置缓存
		Timer timer = new Timer();  
        timer.schedule(new MyTask(), de*1000, de*1000); 
	}
	
	/**
	 * 从配置中心获取配置项
	 * @param placeholder
	 * @return
	 */
	public static String getDbProperty(String placeholder){
		String value = null;
		if(dbPropertyValues == null){
			dbPropertyValues = getDbPropertyValuesFromConfCenter();
		}
		if(dbPropertyValues != null){
			value = dbPropertyValues.get(placeholder);
		}
		return value;
	}
	

	
	public static List<String> getDbPropertyLst(String placeholder){
		List<String> valuelst = new ArrayList<String>();
		if(dbPropertyValues == null){
			dbPropertyValues = getDbPropertyValuesFromConfCenter();
		}
		if(dbPropertyValues != null){
			for (Map.Entry<String, String> entry : dbPropertyValues.entrySet()) {
//				dbPropertyValues.set(entry.getKey(), entry.getValue());
				if(entry.getKey().startsWith(placeholder)){
					valuelst.add(entry.getValue());
				}
			}
//			value = dbPropertyValues.get(placeholder);
		}
		return valuelst;
		
	}
	
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		String value = props.getProperty(placeholder);
		if(value == null){
			value = getDbProperty(placeholder);
		}
		return value;
	}

	/**
	 * 从配置中心读取配置表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String,String> getDbPropertyValuesFromConfCenter(){
		String confCenterAddr = System.getProperty("confCenterAddr");
		if(confCenterAddr == null){
//			confCenterAddr = "http://10.10.0.102:6789/confCenter/conf";
//			confCenterAddr = "http://172.18.111.11:6789/conf-center-web/conf";
//			confCenterAddr = "http://conf.dzswj.foresee:6789/confCenter/conf";
			confCenterAddr = "http://127.0.0.1:8081/confCenter/com.foresee.conf";
		}
		String confTable = System.getProperty("confTable");//-DconfTable=conf_default_kftestxml
		//请求配置中心获取配置表信息，然后载入dbPropertyValues中
		String url = confCenterAddr+(confTable==null?"":"?confTable="+confTable);
		HttpGet get = HttpClientConnectionManager.getGetMethod(url);  
        HttpResponse response = null;
		try {
			log.info("准备请求配置中心获取配置文件，配置表为："+(confTable==null?ConfigurationConstants.DEFAULT_CONFIGURATION_TABLENAME:confTable));
			response = HttpClientConnectionManager.createHttpClient().execute(get);
            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");  
            Map<String,String> values  = new ObjectMapper().readValue(jsonStr, Map.class);  
            updateValues(values);
            log.info("读取配置表"+(confTable==null?ConfigurationConstants.DEFAULT_CONFIGURATION_TABLENAME:confTable)+"成功！");
            return values;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("请求配置中心获取配置信息失败！",e);
		}  
		return null;
	}
	
	/**
	 * @param values
	 */
	private static void updateValues(Map<String, String> values) {
		for (String systemAttr : ConfigurationConstants.SYSTEM_ATTR_LIST) {
			String attrValue = System.getProperty(systemAttr);
			if(!StringUtils.isEmpty(attrValue)){
				if( values.containsKey(systemAttr)){
					values.remove(systemAttr);
				}
				values.put(systemAttr, attrValue);
			}
		}
	}

	/**
	 * 刷新配置中心缓存
	 * @project configuration
	 * @copyright Copyright (c) 2016 foresee
	 * @company foresee
	 * @author Tim
	 * @version 1.0
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	class MyTask extends TimerTask{  
        public void run(){  
        	Map<String,String> values = getDbPropertyValuesFromConfCenter();
        	if(values != null){
        		dbPropertyValues = values;
        	}
        }  
    }
}
