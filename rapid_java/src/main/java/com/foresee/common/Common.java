package com.foresee.common;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;

import com.foresee.service.configuration.utils.ForeseePropertyUtil;
/**
 * 
 * <pre>
 * 公共参数和公共方法
 * </pre>
 *
 * @author weiyang weiyang@foresee.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class Common {
	/**
	 * 用户信息key
	 */
	public static final String USER_KEY = "userLogin";
	
	/**
	 * 字符集 begin
	 */
	public static final String CHARSET_ISO8859_1 = "ISO-8859-1";
	
	/**
	 * 头部图片-国地税类型代码
	 */
	public static final String IMG_GDSLXDM = ForeseePropertyUtil.getDbProperty("admin.img.gdslxdm");
	/**
	 * 头部图片-区域代码
	 */
	public static final String IMG_AREA_CODE = ForeseePropertyUtil.getDbProperty("admin.img.areaCode");
	/**
	 * 字符集end
	 */
	public static final String CHARSET_UTF8 = "UTF-8";
	/**
	 * 公共获取session属性
	 * 
	 * @author yubing
	 * @param request
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Object getSessionAttribute(HttpServletRequest request, String name){
	    HttpSession session = getSession(request);  
	    return (session != null ? session.getAttribute(name) : null);  
	}
	
	/**
	 * 获取当前session,不存放返回null
	 * 
	 * @author yubing
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request){
		if(request==null){
			
		}
		Assert.notNull(request, "Request must not be null!");
		return request.getSession(false);
	}
	/**
	 * MD5加密
	 * @param pwd
	 * @return
	 */
	public final static String MD5(String pwd) {  
        //用于加密的字符  
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中  
            byte[] btInput = pwd.getBytes();  
               
            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
               
            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要  
            mdInst.update(btInput);  
               
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文  
            byte[] md = mdInst.digest();  
               
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {   //  i = 0  
                byte byte0 = md[i];  //95  
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5   
                str[k++] = md5String[byte0 & 0xf];   //   F  
            }  
               
            //返回经过加密后的字符串  
            return new String(str);  
               
        } catch (Exception e) {  
            return null;  
        }  
    }  
	
	public static String getWebPoralURL(HttpServletRequest request){
		String scheme = request.getScheme();
		String curURL = request.getRequestURL().toString();
		String a = curURL;
		a = a.substring(a.indexOf("//")+2);
		a = a.substring(0, a.indexOf("/"));
		a = a.replaceAll("dev1", "dev");
		a = a.replaceAll("dev2", "dev");
		a = a.replaceAll("dev3", "dev");
		a = a.replaceAll("dev4", "dev");
		a = a.replaceAll("agent1", "agent");
		a = a.replaceAll("agent2", "agent");
		a = a.replaceAll("agent3", "agent");
		a = a.replaceAll("agent4", "agent");
//		if(a.indexOf(":")!=-1){
//			a = a.substring(0,a.indexOf(":"));	
//		}
		a += request.getContextPath();
		
		return scheme+"://"+a;
	}
	public static void main(String[] args) {
		System.out.println(MD5("123456"));
	}
}
