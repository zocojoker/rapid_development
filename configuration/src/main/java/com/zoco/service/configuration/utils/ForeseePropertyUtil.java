package com.zoco.service.configuration.utils;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.zoco.service.configuration.property.ForeseePropertyPlaceholder;

/**
 *
 * @project configuration
 * @description 配置文件读取工具
 * @copyright Copyright (c) 2016 zoco
 * @company zoco
 * @author  Tim
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
public class ForeseePropertyUtil {

	/**
	 * 存放各个配置文件，按需加载
	 */
	private static Hashtable<String, Properties> map = new Hashtable<String, Properties>();

	/**
	 * 获取指定配置文件中的指定配置项
	 * 
	 * @param filePath 当该值为null时会尝试从配置中心中获取该配置项
	 * @param propertyName
	 * @return
	 */
	public static String getProperty(String filePath, String propertyName) {
		if(filePath == null){
			return getDbProperty(propertyName);
		}else{
			String property = "";
			if (map.get(filePath) == null) {
				try {
					map.put(filePath, PropertiesLoaderUtils.loadProperties(new ClassPathResource(filePath)));
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
			for (Entry<Object, Object> p : map.get(filePath).entrySet()) {
				String propKey = (String) p.getKey();
				if (propKey.equals(propertyName)) {
					String value = (String) p.getValue();
					if(value.indexOf("$")!=-1){
						value = getDbProperty(value.replace("${", "").replace("}", ""));
					}
					return value;
				}
			}
			return property;
		}
	}
	
	/**
	 * 获取配置中心里面的配置项
	 * @param propertyName  格式为："module.key" 
	 * @return
	 */
	public static String getDbProperty(String propertyName){
		return ForeseePropertyPlaceholder.getDbProperty(propertyName);
	}
	
	/**
	 * 获取配置中心里面所有以propertyName 开始的配置value值
	 * @param propertyName
	 * @return
	 */
	public static List<String> getDbPropertylst(String propertyName){
		return ForeseePropertyPlaceholder.getDbPropertyLst(propertyName);
	}
}
