/**
 * 
 */
package com.zoco.service.configuration.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tim
 *
 */
public class ConfigurationConstants {

	/**
	 * 默认配置文件表名
	 */
	public static String DEFAULT_CONFIGURATION_TABLENAME = "conf_default";

	/**
	 * 系统属性列表
	 */
	public static List<String> SYSTEM_ATTR_LIST = new ArrayList<String>(
			Arrays.asList("srdg.top.client1.nodeId", "srdg.top.client1.rmiCommuPort","srdg.top.client1.rmiRegPort",
					"srdg.admin.client1.nodeId", "srdg.admin.client1.rmiCommuPort","srdg.admin.client1.rmiRegPort",
					"srdg.oams.client1.nodeId", "srdg.oams.client1.rmiCommuPort","srdg.oams.client1.rmiRegPort",
					"srdg.monitor.server1.nodeId","srdg.monitor.server1.rmiCommuPort","srdg.monitor.server1.rmiRegPort"));
}
