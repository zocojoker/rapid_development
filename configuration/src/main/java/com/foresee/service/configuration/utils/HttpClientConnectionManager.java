package com.foresee.service.configuration.utils;


import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.X509Certificate;

/**
 *
 * @project configuration
 * @description http请求工具类
 * @copyright Copyright (c) 2016 foresee
 * @company foresee
 * @author  Tim
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
public class HttpClientConnectionManager {  
      
	private static ThreadLocal<DefaultHttpClient> _threadHttpClient = new ThreadLocal<DefaultHttpClient> ();
    /** 
     * 获取SSL验证的HttpClient 
     * @param httpClient 
     * @return 
     */  
   /* public static HttpClient getSSLInstance1(HttpClient httpClient){  
        ClientConnectionManager ccm = httpClient.getConnectionManager();  
        SchemeRegistry sr = ccm.getSchemeRegistry();  
        sr.register(new Scheme("https", MySSLSocketFactory.getInstance(), 443));  
        httpClient =  new DefaultHttpClient(ccm, httpClient.getParams());  
        return httpClient;  
    }  */
    
    /**
	 * 获取请求URL的网页内容
	 */
	public static HttpClient createHttpClient() {
		DefaultHttpClient httpclient = _threadHttpClient.get();
		if (httpclient != null) {
			return httpclient;
		}
		httpclient = new DefaultHttpClient();
		_threadHttpClient.set(httpclient);

		try{
			TrustManager easyTrustManager = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
	                //To change body of implemented methods use File | Settings | File Templates.
	            }

	            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
	                //To change body of implemented methods use File | Settings | File Templates.
	            }

	            public X509Certificate[] getAcceptedIssuers() {
	                return new X509Certificate[0];  //To change body of implemented methods use File | Settings | File Templates.
	            }
	        };
	        //不校验证书有效性
	        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
	            public boolean verify(String arg0, SSLSession arg1) {
	            	System.out.println("https verifier : don't verify");
	                return true;
	            }
	            public void verify(String arg0, SSLSocket arg1) throws IOException {}
	            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
	            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
	        };

			SSLContext sslcontext = SSLContext.getInstance("TLS");
	        sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
	        SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
	        Scheme sch = new Scheme("https", sf,443);
	        sf.setHostnameVerifier(hostnameVerifier);

	        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		}catch(Exception e){
			e.printStackTrace();
		}
        
		httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
			public void process(final HttpRequest request,
					final HttpContext context) throws HttpException,
					IOException {
				// if (!request.containsHeader("Accept-Encoding")) {
				// request.addHeader("Accept-Encoding", "gzip");
				// }
				if (!request.containsHeader("Accept")) {
					request.addHeader("Accept", "*/*");
				}
				if (request.containsHeader("User-Agent")) {
					request.removeHeaders("User-Agent");
				}
				if (request.containsHeader("Connection")) {
					request.removeHeaders("Connection");
				}
				request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:8.0) Gecko/20100101 Firefox/8.0");
				request.addHeader("Connection", "keep-alive");
				request.addHeader("Referer", "https://dynamic.12306.cn/otsweb/");
				// request.addHeader("Connection", "close");
			}

		});
		httpclient.addResponseInterceptor(new HttpResponseInterceptor() {
			public void process(final HttpResponse response,
					final HttpContext context) throws HttpException,
					IOException {
//				HttpEntity entity = response.getEntity();
			}
		});
		httpclient.setRedirectHandler(new DefaultRedirectHandler());
		return httpclient;
	}
    
	public static HttpClient createHttpClient(int rTimeOut,int sTimeOut) {
		DefaultHttpClient httpclient = _threadHttpClient.get();
		if (httpclient != null) {
			return httpclient;
		}
		BasicHttpParams httpParams = new BasicHttpParams();  
		HttpConnectionParams.setConnectionTimeout(httpParams, rTimeOut);  
        HttpConnectionParams.setSoTimeout(httpParams, sTimeOut);  
		httpclient = new DefaultHttpClient(httpParams);
		_threadHttpClient.set(httpclient);

		try{
			TrustManager easyTrustManager = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
	                //To change body of implemented methods use File | Settings | File Templates.
	            }

	            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
	                //To change body of implemented methods use File | Settings | File Templates.
	            }

	            public X509Certificate[] getAcceptedIssuers() {
	                return new X509Certificate[0];  //To change body of implemented methods use File | Settings | File Templates.
	            }
	        };
	        //不校验证书有效性
	        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
	            public boolean verify(String arg0, SSLSession arg1) {
	            	System.out.println("https verifier : don't verify");
	                return true;
	            }
	            public void verify(String arg0, SSLSocket arg1) throws IOException {}
	            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
	            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
	        };

			SSLContext sslcontext = SSLContext.getInstance("TLS");
	        sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
	        SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
	        Scheme sch = new Scheme("https", sf,443);
	        sf.setHostnameVerifier(hostnameVerifier);

	        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		}catch(Exception e){
			e.printStackTrace();
		}
        
		httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
			public void process(final HttpRequest request,
					final HttpContext context) throws HttpException,
					IOException {
				// if (!request.containsHeader("Accept-Encoding")) {
				// request.addHeader("Accept-Encoding", "gzip");
				// }
				if (!request.containsHeader("Accept")) {
					request.addHeader("Accept", "*/*");
				}
				if (request.containsHeader("User-Agent")) {
					request.removeHeaders("User-Agent");
				}
				if (request.containsHeader("Connection")) {
					request.removeHeaders("Connection");
				}
				request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:8.0) Gecko/20100101 Firefox/8.0");
				request.addHeader("Connection", "keep-alive");
				request.addHeader("Referer", "https://dynamic.12306.cn/otsweb/");
				// request.addHeader("Connection", "close");
			}

		});
		httpclient.addResponseInterceptor(new HttpResponseInterceptor() {
			public void process(final HttpResponse response,
					final HttpContext context) throws HttpException,
					IOException {
//				HttpEntity entity = response.getEntity();
			}
		});
		httpclient.setRedirectHandler(new DefaultRedirectHandler());
		return httpclient;
	}
	
    /** 
     * 模拟浏览器post提交 
     *  
     * @param url 
     * @return 
     */  
    public static HttpPost getPostMethod(String url) {  
        HttpPost pmethod = new HttpPost(url); // 设置响应头信息  
        pmethod.addHeader("Connection", "keep-alive");  
        pmethod.addHeader("Accept", "*/*");  
        pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
//        pmethod.addHeader("Host", "mp.weixin.qq.com");  
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");  
        pmethod.addHeader("Cache-Control", "max-age=0");  
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) "); 
        return pmethod;  
    }  
    /** 
     * 模拟浏览器post提交 
     *  
     * @param url 
     * @param bodyjson 参数
     * @return 
     */  
    public static HttpPost getPostMethod(String url,UrlEncodedFormEntity uefEntity) {  
    	 HttpPost pmethod = new HttpPost(url); // 设置响应头信息  
         pmethod.addHeader("Connection", "keep-alive");  
         pmethod.addHeader("Accept", "*/*");  
         pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); 
         //pmethod.addHeader("Content-Type", "multipart/form-data;boundary=" + "This-is-the-hwping-ending");
//         pmethod.addHeader("Host", "mp.weixin.qq.com");  
         pmethod.addHeader("X-Requested-With", "XMLHttpRequest");  
         pmethod.addHeader("Cache-Control", "max-age=0");  
         pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) "); 
         pmethod.setEntity(uefEntity);
         return pmethod;  
    }  
  
    /**  
     * 模拟浏览器GET提交  
     * @param url  
     * @return  
     */  
    public static HttpGet getGetMethod(String url) {  
		try {
			URL urlTemp = new URL(url);
			URI uri = new URI(urlTemp.getProtocol(), urlTemp.getAuthority(), urlTemp.getPath(), urlTemp.getQuery(), null);
	    	HttpGet pmethod = new HttpGet(uri);

	        // 设置响应头信息  
	        pmethod.addHeader("Connection", "keep-alive");  
	        pmethod.addHeader("Cache-Control", "max-age=0");  
	        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");  
	        pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");  
	        return pmethod;  
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	return new HttpGet(url);
    }
	
	public static HttpPost getPostMethod(String url, HttpEntity bentiy) {
		HttpPost pmethod = new HttpPost(url); // 设置响应头信息  
        pmethod.addHeader("Connection", "keep-alive");  
        pmethod.addHeader("Accept", "*/*");  
        pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");  
        pmethod.addHeader("Cache-Control", "max-age=0");  
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) "); 
        pmethod.setEntity(bentiy);
        return pmethod;
	}
}  