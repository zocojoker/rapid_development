/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.fbrp.util.xml;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

/**
 * <pre>
 * Vtd 处理xml工具。
 * </pre>
 * 
 * @author wangbozheng wangbozheng@zoco.com.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *    1.01          wangfm  2012-10-10   增加方法getVTDNav和selectByXpathWithVTDNav
 * </pre>
 */
public class VtdXmlOperateUtil {
	
	/** 日志 **/
	private static final Log logger = LogFactory
			.getLog(VtdXmlOperateUtil.class);

	/**
	 * 根据xml获得对应的VTDNav
	 * @param xml
	 * @return
	 * @throws Exception 
	 */
	public static VTDNav getVTDNav(String xml) throws Exception {
		VTDGen gen = new VTDGen();
		VTDNav nav = null;
		if (xml == null || xml.equals("")) {
			return null;
		}
		byte[] bs;
		try {
			bs = xml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("xml encoding is not utf-8！");
			throw e1;
		}
		gen.setDoc(bs);
		try {
			gen.parse(false);
			nav = gen.getNav();
		} catch (Exception e) {
			throw e;
		}
		return nav;
	}

	
	/**
	 * 返回根据xpath表达式选择的xml节点信息。
	 * 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
	 * List<VtdSelectResultWrapper>.
	 * 此方法结合getVTDNav方法，替代selectByXpath，用来提升VTDXml的效率，避免多次的VTDGen操作
	 * 如果需要从1个xml中多次读取数据，请用此方法
	 * @param nav
	 * @param xpath
	 * @return
	 * @throws Exception 
	 */
	public static Object selectByXpathWithVTDNav(VTDNav nav, String xpath) throws Exception {
		List<VtdSelectResultWrapper> lst = new java.util.ArrayList<VtdSelectResultWrapper>();
		try {
			AutoPilot autoPilot = new AutoPilot(nav);
			autoPilot.selectXPath(xpath);
			int pos = -1;
			pos = autoPilot.evalXPath();
			while (pos > 0) {
				VtdSelectResultWrapper wrapper = new VtdSelectResultWrapper();
				wrapper.setNodeName(nav.toString(pos));
				wrapper.setText(getNodeText(nav,true));
				if (nav.getText() < 0){
					wrapper.setXmlNode(true);
				}
				lst.add(wrapper);
				pos = autoPilot.evalXPath();
			}
		} catch (Exception e) {
			throw e;
		}
		if ((lst.size() == 1) && !lst.get(0).isXmlNode()){
			return lst.get(0).getText();
		}
		return lst;
	}
	/**
	 * @param inputXml
	 * @param insertNote
	 * @throws UnsupportedEncodingException 
	 * @function 往xml指定节点之前中插入数据，并返回操作完成后的xml文件
	 * 
	 */
	public static String insertNoteToInputXml(String inputXml,String insertNote,String insertPath) throws UnsupportedEncodingException{
		String xml="";
		VTDGen gen = new VTDGen();
		if (inputXml == null || inputXml.equals("")) {
			return null;
		}
		byte[] bs;
		try {
			bs = inputXml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("xml encoding is not utf-8！");
			throw e1;
		}
		try{
		ByteArrayOutputStream bao=new ByteArrayOutputStream();
		gen.setDoc(bs);
		 int i;
         AutoPilot ap = new AutoPilot();
		  ap.selectXPath(insertPath);
         XMLModifier xm = new XMLModifier();
         gen.parse(false);
             VTDNav vn = gen.getNav();
             ap.bind(vn);
             xm.bind(vn);
             while ((i = ap.evalXPath()) != -1)
             {
                 xm.insertBeforeElement(insertNote);
             }
             xm.output(bao);
             xml=bao.toString();
		     bao.close();
		     return xml;
		}catch(Exception e){
			return "";
		}
	}
	/**
	 * @param inputXml
	 * @param insertNote
	 * @throws UnsupportedEncodingException 
	 * @function 修改指定节点里的数据内容，并返回操作完成后的xml文件
	 */
	public static String updateNoteToInputXml(String inputXml,String insertNote,String insertPath) throws UnsupportedEncodingException{
		inputXml = createXpath(inputXml,insertPath);
		String xml="";
		VTDGen gen = new VTDGen();
		if (inputXml == null || inputXml.equals("")) {
			return null;
		}
		byte[] bs;
		try {
			bs = inputXml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("xml encoding is not utf-8！");
			throw e1;
		}
		try{
		ByteArrayOutputStream bao=new ByteArrayOutputStream();
		gen.setDoc(bs);
		 int i=-1;
          AutoPilot ap = new AutoPilot();
		  ap.selectXPath(insertPath+"/text()");
         XMLModifier xm = new XMLModifier();
          gen.parse(false);
             VTDNav vn = gen.getNav();
             ap.bind(vn);
             xm.bind(vn);
             while ((i = ap.evalXPath()) != -1)
             {
                 xm.updateToken(i,insertNote);  
             }
             xm.output(bao);
             xml=bao.toString();
		     bao.close();
		     return xml;
		}catch(Exception e){
			return "";
		}
	}
	
	
	/**
	 * @param inputXml
	 * @param insertNote
	 * @throws UnsupportedEncodingException 
	 * @function 删除指定节点，并返回操作完成后的xml文件
	 */
	public static String removeNoteToInputXml(String inputXml,String insertNote,String insertPath) throws UnsupportedEncodingException{
		String xml="";
		VTDGen gen = new VTDGen();
		if (inputXml == null || inputXml.equals("")) {
			return null;
		}
		byte[] bs;
		try {
			bs = inputXml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("xml encoding is not utf-8！");
			throw e1;
		}
		try{
		ByteArrayOutputStream bao=new ByteArrayOutputStream();
		gen.setDoc(bs);
		 int i=-1;
          AutoPilot ap = new AutoPilot();
		  ap.selectXPath(insertPath);
         XMLModifier xm = new XMLModifier();
          gen.parse(false);
             VTDNav vn = gen.getNav();
             ap.bind(vn);
             xm.bind(vn);
             while ((i = ap.evalXPath()) != -1)
             {
                 xm.remove();  
             }
             xm.output(bao);
             xml=bao.toString();
		     bao.close();
		     return xml;
		}catch(Exception e){
			return "";
		}
	}
	
	
	
	
	/**
	 * 返回根据xpath表达式选择的xml节点信息。
	 * 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
	 * List<VtdSelectResultWrapper>.
	 * 此方法每次根据xml形成VTDGen，如果需要从xml中多次获取数据，效率比较低。
	 * @param xml 字符串.
	 * @param xpath 表达式.
	 * @return List<VtdSelectResultWrapper> or String .
	 * @throws Exception 
	 */
	public static Object selectByXpath(String xml, String xpath) throws Exception {

		VTDGen gen = new VTDGen();
		if (xml == null || xml.equals("")) {
			return null;
		}
		byte[] bs;
		try {
			bs = xml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("xml encoding is not utf-8！");
			throw e1;
		}
		gen.setDoc(bs);
		List<VtdSelectResultWrapper> lst = new java.util.ArrayList<VtdSelectResultWrapper>();
		try {
			gen.parse(false);
			VTDNav nav = gen.getNav();
			AutoPilot autoPilot = new AutoPilot(nav);
			autoPilot.selectXPath(xpath);
			int pos = -1;
			pos = autoPilot.evalXPath();
			while (pos > 0) {
				VtdSelectResultWrapper wrapper = new VtdSelectResultWrapper();
				wrapper.setNodeName(nav.toString(pos));
				wrapper.setText(getNodeText(nav,true));
				if (nav.getText() < 0){
					wrapper.setXmlNode(true);
				}
				lst.add(wrapper);
				pos = autoPilot.evalXPath();
			}
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		}
		if ((lst.size() == 1) && !lst.get(0).isXmlNode()){
			return lst.get(0).getText();
		}
		
		return lst;
	}

	/**
	 * 获取VTDNav当前节点标签的文本值。
	 * 
	 * @param nav VTDNav.
	 * @param wrappeTag 是否包装节点标签。
	 * @return String 。
	 * @throws Exception 
	 * @throws NavException 。
	 */
	public static String getNodeText(VTDNav nav,boolean IsWrappeTag) throws Exception {
		try {
			int pos = nav.getText();
			if (pos < 0) {
				long posInf = -1;
				if (IsWrappeTag){  
				   posInf = nav.getElementFragment();
				} else{
				   posInf = nav.getContentFragment();
				}
				return nav.toRawString((int)posInf, (int)(posInf >> 32));
			} else {
				return nav.toString(pos);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @param inputXml
	 * @param insertNote
	 * @throws UnsupportedEncodingException 
	 * @function 在指定节点中增加xml，并返回
	 */
	public static String updateNode(String inputXml,String insertNote,String insertPath) throws UnsupportedEncodingException{
		inputXml = createXpath(inputXml,insertPath);
		String xml="";
		VTDGen gen = new VTDGen();
		if (inputXml == null || inputXml.equals("")) {
			return null;
		}
		byte[] bs;
		try {
			bs = inputXml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("xml encoding is not utf-8！");
			throw e1;
		}
		try{
		ByteArrayOutputStream bao=new ByteArrayOutputStream();
		
		gen.setDoc(bs);
		 int i;
         AutoPilot ap = new AutoPilot();
		  ap.selectXPath(insertPath);
         XMLModifier xm = new XMLModifier();
         gen.parse(false);
             VTDNav vn = gen.getNav();
             ap.bind(vn);
             xm.bind(vn);
             while ((i = ap.evalXPath()) != -1)
             {
//            	 在内容中插入
                 xm.insertAfterHead(insertNote); 
//                 节点标签中插入
//                 xm.insertAttribute(insertNote);
//            	 替换标签
//                 xm.updateElementName(insertNote);
             }
             xm.output(bao);
             xml= bao.toString("UTF-8");
		     bao.close();
		     return xml;
		}catch(Exception e){
			return "";
		}
	}
	
	
	/**
	 * 查询该结点是否存在
	 * @param xml
	 * @param xpath
	 * @return
	 */
	
	public static boolean queryByXpath(String xml, String xpath) {

		boolean rst=false;
		VTDGen gen = new VTDGen();
		if (xml == null || xml.equals("")) {
			return rst;
		}
		byte[] bs=null;
		try {
			bs = xml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			//throw new Exception("xml encoding is not utf-8！");
		}
		gen.setDoc(bs);
		List<VtdSelectResultWrapper> lst = new java.util.ArrayList<VtdSelectResultWrapper>();
		try {
			gen.parse(false);
			VTDNav nav = gen.getNav();
			AutoPilot autoPilot = new AutoPilot(nav);
			autoPilot.selectXPath(xpath);
			int pos = -1;
			pos = autoPilot.evalXPath();
			if(pos!=-1)
			{
				while (pos > 0) {
					VtdSelectResultWrapper wrapper = new VtdSelectResultWrapper();
					wrapper.setNodeName(nav.toString(pos));
					wrapper.setText(getNodeText(nav,true));
					if (nav.getText() < 0){
						wrapper.setXmlNode(true);
					}
					lst.add(wrapper);
					pos = autoPilot.evalXPath();
				}
				rst = true;
			}else
			{
				rst=false;
			}
		} catch (Exception e) {
			rst=false;
			//throw new Exception(e);
			
		}
	
		
		
		return rst;
	}
	
	/**
	 * 创建xpath
	 * @param inputXml
	 * @param xPath
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String createXpath(String inputXml,String xPath) throws UnsupportedEncodingException{
        String[] xPaths = xPath.split("/");
        xPath = "";
        for(String path:xPaths){
      	  if(path==null||path.equals("")){continue;}
      	  xPath += "/"+path;
	      	String xml="";
			VTDGen gen = new VTDGen();
			if (inputXml == null || inputXml.equals("")) {
				return null;
			}
			byte[] bs;
			try {
				bs = inputXml.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				logger.error("xml encoding is not utf-8！");
				throw e1;
			}
			try{
			ByteArrayOutputStream bao=new ByteArrayOutputStream();
			gen.setDoc(bs);
	        gen.parse(false);
	        VTDNav vn = gen.getNav();
	          AutoPilot ap = new AutoPilot(vn);
	          XMLModifier xm = new XMLModifier(vn);
        	  ap.selectXPath(xPath);
        	  if(ap.evalXPath() == -1){//不存在该节点
        		  ap.selectXPath(xPath.substring(0, xPath.lastIndexOf("/")));
        		  if(ap.evalXPath() != -1){
            		  xm.insertAfterHead("<"+path+"></"+path+">");
        		  }
        	  }
	         xm.output(bao);
	         xml=bao.toString();
		     bao.close();
		     inputXml = xml;
			}catch(Exception e){
				return "";
			}
        }
        return inputXml;
	}
	
    /**
     * 替换xml值的特殊字符,<,&
     * 替换xml值里面的特殊字符,严格来说只有<和&是需要替换的,这里替换<>&'"这五个
     * @param str
     * @return
     */
    public static String charReplace(String str){
    	if(null!=str){
        	return str.replaceAll("&", "&amp;")
        			.replaceAll("<", "&lt;")
            		.replaceAll(">", "&gt;")
            		.replaceAll("\"", "&quot;")
            		.replaceAll("'", "&apos;");
    	}
    	return "";
    }
	
    /**
     * 反替换xml值的特殊字符,<,&
     * 替换xml值里面的特殊字符,严格来说只有<和&是需要替换的,这里替换<>&'"这五个
     * @param str
     * @return
     */
    public static String deCharReplace(String str){
    	if(null!=str){
        	return str.replaceAll("&amp;", "&")
        			.replaceAll("&lt;", "<")
            		.replaceAll("&gt;", ">")
            		.replaceAll("&quot;", "\"")
            		.replaceAll("&apos;", "'");
    	}
    	return "";
    }
	/**
	 * @param xml  两级xml
	 * @param node
	 * @return
	 */
	public static String parseXmlOneNode(String xml,String node){
		Document document = null;
		Element att = null;
		try {
			document = DocumentHelper.parseText(xml);
			att = document.getRootElement().element(node);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if(att!=null)
			return att.getText()!=null?att.getText():"";
		else
			return "";
	}
}
