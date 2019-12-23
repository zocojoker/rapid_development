package com.zoco.LeetCode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的
 * ，并且它们的每个节点只能存储 一位 数字。  如果，我们将这两个数相加起来，则会返回一个新的链表
 * 来表示它们的和。  您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author zoco
 * @creat 2019-10-15-9:20
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);

        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        l1.next = l11;
        l11.next = l12;

        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        l2.next = l21;
        l21.next = l22;
//        System.out.println(m(new ListNode(9)));
        ListNode l = addTwoNumbers(l1, l2);
        System.out.println(l.val + ">" + l.next.val + ">" + l.next.next.val);
        //System.out.println("807".charAt(2));

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String s = m(l1).add(m(l2)).toString();
        ListNode ln;
        List<ListNode> list = new ArrayList();
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.println(Integer.parseInt(s.charAt(i) + ""));
            ln = new ListNode(Integer.parseInt(s.charAt(i) + ""));
            list.add(ln);
            if (j != 0) {
                list.get(j - 1).next = ln;
            }
            j++;
        }
        return list.get(0);
    }

    private static BigInteger m(ListNode l1) {
        StringBuffer buf = new StringBuffer();
        ListNode l = l1;
        do {
            buf.append(l.val);
            l = l.next;
        } while (l != null);
        if (buf == null || "".equals(buf)) {
            return new BigInteger("0");
        } else {
            buf = buf.reverse();
            return new BigInteger(buf.toString());
        }

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}