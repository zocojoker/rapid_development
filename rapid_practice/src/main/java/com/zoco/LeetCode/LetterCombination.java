package com.zoco.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoco on 2019/9/3.
 */
public class LetterCombination {
	/**
	 * 当前迭代数
	 */
	private static int ITER_NUM = 1;
	/**
	 * 迭代最大次数
	 */
	private static int ITER_MAX_COUNT = 1;

	private static List<String> COMB = new ArrayList<>();

	static final char[] c2 = new char[] { 'A', 'B', 'C' };
	static final char[] c3 = new char[] { 'D', 'E', 'F' };
	static final char[] c4 = new char[] { 'G', 'H', 'I' };
	static final char[] c5 = new char[] { 'J', 'K', 'L' };
	static final char[] c6 = new char[] { 'M', 'N', 'O' };
	static final char[] c7 = new char[] { 'P', 'Q', 'R', 'S' };
	static final char[] c8 = new char[] { 'T', 'U', 'V' };
	static final char[] c9 = new char[] { 'W', 'X', 'Y', 'Z' };

	public static void main(String[] args) {
		getCombinations(new int[] { 1, 0 });
	}

	public static void getCombinations(int[] arr) {

		List<char[]> chars = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
			case 2:
				chars.add(c2);
				break;
			case 3:
				chars.add(c3);
				break;
			case 4:
				chars.add(c4);
				break;
			case 5:
				chars.add(c5);
				break;
			case 6:
				chars.add(c6);
				break;
			case 7:
				chars.add(c7);
				break;
			case 8:
				chars.add(c8);
				break;
			case 9:
				chars.add(c9);
				break;
			default:
				break;
			}
		}
		int len = chars.size();
		if (len < 1) {
			System.out.println("NO COMBINATION!");
		} else {// 获取迭代最大次数
			ITER_MAX_COUNT = chars.size();

			getCombList(chars, new String());
			for (String str : COMB) {
				System.out.print(str + " ");
			}
		}

	}

	/**
	 * 获取组合总数
	 *
	 * @param list
	 * @return
	 */
	public static int getCombinationsCount(List<char[]> list) {
		int combinationsCount = 1;
		for (char[] c : list) {
			combinationsCount = combinationsCount * c.length;
		}
		return combinationsCount;
	}

	public static void getCombList(List<char[]> chars, String str) {
		char[] chs = chars.get(ITER_NUM - 1);
		for (int i = 0; i < chs.length; i++) {
			String s = str + String.valueOf(chs[i]);
			if (ITER_NUM < ITER_MAX_COUNT) {
				ITER_NUM++;
				getCombList(chars, s);
			} else {
				COMB.add(s);
			}

		}
		ITER_NUM--;
	}

}
