package com.zoco.LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给出一串数字字符传，输出所有可能的IP 例：21212121 就有21.21.21.21 2019年10月31日
 * 
 * @author zoco
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class AllIP {
	public static void main(String[] args) {
		String str = "12700111";
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<String>();
		try {
			if (str.length() < 4 || str.length() > 12)
				throw new Exception("传入字符串长度不符合ip规范");
			if (str.length() == 4) {
				char[] chars = str.toCharArray();
				set.add(chars[0] + "." + chars[1] + "." + chars[2] + "." + chars[3]);
				return;
			}
			if (str.length() == 12) {
				char[] chars = str.toCharArray();
				set.add(chars[0] + chars[1] + chars[2] + "." + chars[3] + chars[4] + chars[5] + "." + chars[6]
						+ chars[7] + chars[8] + "." + chars[9] + chars[10] + chars[11]);
				return;
			}

			getApp("", str, list, 0);
			// dfs(str, 0, "", set);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.util.Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		for (String s : list) {
			System.out.println(s);
		}
	}

	public static List<String> getApp(String ips, String str, List<String> set, int count) throws Exception {
		if (count >= 4) {
			if (str.isEmpty())
				set.add(ips);
			return set;
		}
		for (int i = 0; i < 3; i++) {
			if (str.length() < i + 1) {
				break;
			}
			getApp(ips + str.substring(0, i + 1) + (count < 3 ? "." : ""), str.substring(i + 1), set, count + 1);
		}
		return set;
	}

	/**
	 * 网上摘抄的
	 * 
	 * @param s
	 *            剩下的字符串
	 * @param len
	 *            当前循环的次数
	 * @param out
	 *            输出的ip格式字符传
	 * @param set
	 */
	public static void dfs(String s, int len, String out, Set<String> set) {
		if (len == 4) {
			if (s.isEmpty())
				set.add(out);
			return;
		}
		for (int k = 1; k <= 3; k++) {
			if (s.length() < k)
				break;
			int val = Integer.parseInt(s.substring(0, k));
			if (k != String.valueOf(val).length())
				continue;
			dfs(s.substring(k), len + 1, out + s.substring(0, k) + (len == 3 ? "" : "."), set);
		}
	}
}
