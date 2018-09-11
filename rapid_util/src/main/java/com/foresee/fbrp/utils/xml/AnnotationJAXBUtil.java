/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.fbrp.utils.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * <pre>
 *  基于注解的java对象和xml绑定工具类.
 * </pre>
 * @author wangbozheng  wangbozheng@foresee.com.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class AnnotationJAXBUtil {
   
	/**
     *  xml转javaBean对象.
     * @param cls javaBean class 
     * @param xml xml报文
     * @return Object
     */
	public static Object toJavaBean(Class<?> cls,String xml){
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			Unmarshaller um = context.createUnmarshaller();
			return um.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			throw new RuntimeException("错误的报文内容: \n" + xml, e);
		} catch (NullPointerException e) {
			throw new RuntimeException("错误的报文内容: \n" + xml, e);
		}
    }
    
	
    /**
     * javaBean对象转xml. 
     * @param javaBean javaBean。
     * @param forceFixCData 强制修复cdata的转移字符。
     * @return String
     */
    public static String toXml(Object javaBean,boolean forceFixCData){
		try {
			JAXBContext context = JAXBContext.newInstance(javaBean.getClass());
			Marshaller ma = context.createMarshaller();
			//ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			ma.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
			ma.setProperty(Marshaller.JAXB_FRAGMENT, false);
			StringWriter outStream = new java.io.StringWriter();
			ma.marshal(javaBean, outStream);
			String xml = outStream.toString();
			// 替换掉转移字符
			if (forceFixCData){
			  xml = xml.replaceAll("&lt;", "<");
			  xml = xml.replaceAll("&gt;", ">");
			}
			return xml;
		} catch (JAXBException e) {
			throw new RuntimeException (e);
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
		}
    }
}
