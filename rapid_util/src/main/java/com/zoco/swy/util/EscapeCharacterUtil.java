package com.zoco.swy.util;

import com.zoco.fbrp.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <pre>
 * 特殊字符转义处理工具
 * </pre>
 *
 * @author hushengping hushengping@zoco.com.cn
 * @version 1.00.00
 *
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class EscapeCharacterUtil {
	/**
	 * 转义特殊字符。
	 * @param escapeCharacterListStr 特殊字符转义配置列表
	 * @param originalText 原始文本
	 * @return  返回转义后文本
	 */
	public static String escapeSpecialCharacter(String escapeCharacterListStr, String originalText) {
		List<EscapeTextHolder> escapeCharacterList = setEscapeCharacterListStr(escapeCharacterListStr);
		String resultText = originalText;
		for (EscapeTextHolder hoder : escapeCharacterList) {
			if (!StringUtil.isEmpty(originalText)) {
				resultText = resultText.replaceAll(hoder.getOriginal(), hoder.getEscape());
			}
		}
		return resultText;
	}

	/**
	 * 返回 escapeCharacterList。
	 * 
	 * @param escapeCharacterList
	 *            设置 escapeCharacterList。
	 */
	private static List<EscapeTextHolder> setEscapeCharacterListStr(String escapeCharacterListStr) {
		List<EscapeTextHolder> escapeCharacterList = new ArrayList<EscapeCharacterUtil.EscapeTextHolder>();
		if (!StringUtil.isStrEmpty(escapeCharacterListStr)) {
			String[] ecsapeArr = escapeCharacterListStr.split("-");
			for (String escape : ecsapeArr) {
				String[] temp = escape.split(":");
				if (temp.length == 2) {
					EscapeTextHolder holder = new EscapeTextHolder(temp[0].trim(), temp[1].trim());
					escapeCharacterList.add(holder);
				}
			}
		}
		return escapeCharacterList;
	}

	/**
	 * 
	 * <pre>
	 * 特殊字符处理器对象。
	 * </pre>
	 * 
	 * @author hushengping  hushengping@zoco.com.cn
	 * @version 1.00.00
	 * 
	 *          <pre>
	 * 修改记录
	 *    修改后版本:     修改人：  修改日期:     修改内容:
	 * </pre>
	 */
	private static class EscapeTextHolder {
		private String original;
		private String escape;

		public EscapeTextHolder(String original, String escape) {
			super();
			this.original = original;
			this.escape = escape;
		}

		/**
		 * 返回 original。
		 * 
		 * @return 返回 original。
		 */
		public String getOriginal() {
			return original;
		}

		/**
		 * 返回 escapte。
		 * 
		 * @return 返回 escapte。
		 */
		public String getEscape() {
			return escape;
		}

	}
}
