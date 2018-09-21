package com.zoco.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zoco.common.Common;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <pre>
 * 登录认证拦截器
 * </pre>
 *
 * @author weiyang weiyang@zoco.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
@SuppressWarnings("unused")
public class AuthenticationFilter implements Filter {

	private static final String sessionTip = "认证过期，请重新登录...";
	/**
	 * 日志
	 */
	protected final static Logger logger = Logger.getLogger(AuthenticationFilter.class);
	private String excludes = "";
	private String context = "";
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		String context = req.getContextPath();
		String res = req.getRequestURI().replaceAll(context, "");
		
		HttpServletResponse httpRes = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		Object userData = session.getAttribute(Common.USER_KEY);
		
		if(res.equals("/") || res.equals("/login.jsp")){
			StringBuffer reqUrl = req.getRequestURL();
			if(userData!=null && res.equals("/")){
				httpRes.sendRedirect(reqUrl.append("/index.jsp").toString());
			}else if(userData!=null && res.equals("/login.jsp")){
				String baseUrl =  reqUrl.substring(0, reqUrl.indexOf("/login.jsp"));
				httpRes.sendRedirect(baseUrl+"/login.jsp");
			}else{
				chain.doFilter(request, response);
				return;
			}
		}
		
		String method = req.getMethod();
		Map<String, Object> data = new HashMap<String, Object>();
		if (userData==null) {
			// 下面几个操作跳过，不需要权限认证
			if (jumpAuth(url)) {
				httpRes.setCharacterEncoding(Common.CHARSET_UTF8);
				//必须添加,否则IE低版本不支持websocket,会不断地建立连接,不断报错!!!
				if(url.endsWith(".ws")){
					httpRes.setContentType("application/json; charset=" + Common.CHARSET_UTF8);
					httpRes.setHeader("Websocket-Token", new Date().toString());
				}
				chain.doFilter(request, httpRes);
				return;
			}else if("POST".equals(method)){
				data.put("type", "sessionClosed");
				data.put("dumpUrl", Common.getWebPoralURL(req));
				data.put("isPostTimeout", true);
				data.put("warn", sessionTip);
				String jsonResponse = JSONObject.toJSONString(data);
				response.setCharacterEncoding(Common.CHARSET_UTF8);
				PrintWriter printWriter = null;
				try {
					printWriter = response.getWriter();
					printWriter.write(jsonResponse);
					printWriter.flush();
				} catch (IOException e) {
					logger.error(this, e);
				} finally {
					if (printWriter != null) {
						IOUtils.closeQuietly(printWriter);
					}
				}
				if (logger.isDebugEnabled()) {
					logger.debug("[CONT] /AuthenticationFilter/doFilter/" + ": JSON-RESULT: " + jsonResponse);
				}
				return;
			}else{
			  String logOutUrl=Common.getWebPoralURL(req);
			  
				// 获取用户的timeout提示信息
				response.setCharacterEncoding("UTF-8");
				response.getWriter()
						.println(
								"<HTML><HEAD><TITLE></TITLE><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>"
										+ "</HEAD><BODY><script>top.document.location='"
										+ logOutUrl
										+ "'</script></BODY></HTML>");
				
				
				return;
			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		excludes = config.getInitParameter("excludes");
		context = config.getServletContext().getContextPath();
	}

	public boolean jumpAuth(String url) {
		boolean result = false;
		if (url.endsWith(".js")
				|| url.endsWith(".css")
				|| url.contains("img")
				|| url.contains("fonts")
				|| url.contains("dtree")
				|| url.contains("login.jsp")
				|| url.contains("/login.do")
				|| url.contains("topImg.do")
				) {   
			result = true;
		}
		return result;
	}
}
