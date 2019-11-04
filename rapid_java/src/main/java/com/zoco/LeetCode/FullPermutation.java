package com.zoco.LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 字符串全排列和组合排列 2019年10月31日
 * <p>
 * <p>
 * 思路：先抽取新组合的第一个位置的字符，并移除掉原先字符串的数组，再迭代在剩余字符串中抽取第二、第三、第n个位置的字符
 *
 * @author zoco
 * @version 1.00.00
 * <p>
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class FullPermutation {
    private static int LENGTH = 0;
    private static int COUNT = 0;

    public static void main(String[] args) {
        String str = "abcd";
        Set<String> set = new HashSet<>();
        try {
            LENGTH = str.length();
            //Set<String> set = fullPer(str, new String(), new HashSet<String>());
            /*for (int i = 0; i < str.length(); i++) {
                str = str.substring(i);
                set.addAll(FullCombination(str, new String(), new HashSet<String>(), 0));
            }*/
            set = FullCombination1(str, new String(), new HashSet<String>());

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
     * 全组合
     *
     * @return
     */
    public static Set<String> FullCombination1(String str, String newStr, Set<String> set) throws Exception {
        if (str == null || "".equals(str))
            throw new Exception("字符串不允许为空");
        if (str.length() == 1) {
            set.add(newStr + str);
        } else {
            for (int i = 0; i < str.length(); i++) {
                set.add(newStr + str.charAt(i));
                if (str.substring(i + 1).isEmpty()) break;
                FullCombination1(str.substring(i + 1), newStr + str.charAt(i), set);
            }
        }
        return set;
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
     * 全组合,失败品（abc）漏了ac
     *
     * @return
     */
    public static Set<String> FullCombination(String str, String newStr, Set<String> set, int i) throws Exception {
        if (str == null || "".equals(str))
            throw new Exception("字符串不允许为空");
        if (str.length() == 1) {
            System.out.println(newStr + str);
            set.add(newStr + str);
        } else {
            //for (int i = 0; i < str.length(); i++) {
            set.add(newStr + str.charAt(i));
            FullCombination(removeChar(i, str), newStr + str.charAt(i), set, i++);
            //}
        }

        return set;
    }


    private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            System.out.println(Arrays.asList(resultList));
            return;
        }
        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }

}
