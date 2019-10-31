package com.zoco.LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串全排列 2019年10月31日
 * 
 * 
 * 思路：先抽取新组合的第一个位置的字符，并移除掉原先字符串的数组，再迭代在剩余字符串中抽取第二、第三、第n个位置的字符
 * 
 * @author zoco
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class FullPermutation {

	public static void main(String[] args) {
		String str = "abcdf";
		try {
			Set<String> set = fullPer(str, new String(), new HashSet<String>());
			java.util.Iterator<String> it = set.iterator();
			System.out.println("================================");
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 全排列
	 * 
	 * @param str
	 * @param newStr
	 * @param set
	 * @return
	 * @throws Exception
	 */
	public static Set<String> fullPer(String str, String newStr, Set<String> set) throws Exception {
		if (str == null || "".equals(str)) {
			System.out.println("字符串不允许为空");
			throw new Exception("字符串不允许为空");
		}
		if (str.length() == 1) {
			System.out.println(newStr + str);
			set.add(newStr + str);
		} else {
			for (int i = 0; i < str.length(); i++) {

				fullPer(removeChar(i, str), newStr + str.charAt(i), set);
			}
		}

		return set;
	}

	private static String removeChar(int index, String str) {
		String str1 = str.substring(0, index);
		String str2 = str.substring(index + 1);
		return str1 + str2;
	}

	/**
	 * 全组合
	 * 
	 * @return
	 */
	public static Set<String> FullCombination() {
		
		return null;
	}
}
