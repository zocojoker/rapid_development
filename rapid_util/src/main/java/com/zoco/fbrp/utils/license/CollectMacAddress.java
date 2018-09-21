/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.fbrp.utils.license;

import com.zoco.fbrp.utils.InternationalizationUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * <pre>
 * 收集Mac地址。
 * </pre>
 * @author luoshifei luoshifei@zoco.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class CollectMacAddress {

	private static final Log log = LogFactory.getLog(CollectMacAddress.class);
	
	/**
	 * 返回本机的所有Mac地址列表。
	 * 
	 * @return Mac地址列表
	 */
	public static List<String> getMacAddress() {		
		List<String> macAddressList = new ArrayList<String>();
		String os = InternationalizationUtil.toLowerCase(System.getProperty("os.name"));
		if (os != null && os.startsWith("windows")) {
			BufferedReader br = null;
			Process p = null;
			try {
				String command = "cmd.exe /c chcp 437 && ipconfig /all"; //先在cmd中执行设置字符集的命令chcp 437,设置为USA国，否则得不到E文的输出信息
				p = Runtime.getRuntime().exec(command);				
				br = new BufferedReader(new InputStreamReader(p
						.getInputStream(), "GBK")); //命令行缺省执行后得到的是UTF-8字符集数据，转换成GBK进行读取，以显示中文
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("Physical Address") > 0) {
						int index = line.indexOf(":");
						index += 2;
						macAddressList.add(line.substring(index).trim());
					}
				}
				return macAddressList;
			} catch (Exception e) {
				log.info("", e);
			} finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						log.error("", e);
					}
				}
				if(p != null){
					//终止子进程
					p.destroy();
				}
			}
		}
		return macAddressList;
	}

	public static void main(String[] args) {
		List<String> lst = CollectMacAddress.getMacAddress();
		for (String str:lst) {
			System.out.println(str);
		}
	}
}
