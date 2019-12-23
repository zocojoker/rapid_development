package com.zoco.LeetCode;

/**
 * 双向链表排序
 *
 * @author zoco
 * @creat 2019-12-13-14:50
 */
public class LinkedSort {
    /**
     * 双向链表
     *
     * @param <T>
     */
    static class Node<T> {
        Node<T> next;
        Node<T> pre;
        T val;

        public Node(Node<T> next, Node<T> pre, T value) {
            this.next = next;
            this.pre = pre;
            this.val = value;
        }

        public Node(T value) {
            this.val = value;
        }
    }

    public Node sortList(Node head) {
        if (head == null || head.next == null) return head;

        Node mid = getMidNode(head);// 获取中间节点
        Node left = head;
        Node right = mid.next;
        mid.next = null;  // 这一点很重要，不然可能出现循环链表
        return merge(sortList(left), sortList(right));
    }

    // 合并链表，代码比较长，但是很好理解，头节点需要单独考虑一下
    public Node merge(Node<Integer> left, Node<Integer> right) {
        if (left == null) return right;
        if (right == null) return left;

        Node head = null, p = null; // head 头节点，p用于遍历操作
        while (left != null && right != null) {
            if (head == null) {
                if (left.val < right.val) {
                    head = left;
                    left = left.next;
                } else {
                    head = right;
                    right = right.next;
                }
                p = head;
            } else {
                if (left.val < right.val) {
                    p.next = left;
                    left = left.next;
                } else {
                    p.next = right;
                    right = right.next;
                }
                p = p.next;
            }
        }
        // 对剩下的节点进行merge
        if (left != null) p.next = left;
        else p.next = right;
        return head;
    }

    // 使用快慢指针快速找到中间节点
    public Node getMidNode(Node node) {
        if (node == null || node.next == null) return node;
        Node low = node;
        Node fast = node;
        while (fast.next != null && fast.next.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }

    public static Node quickSort(Node<Integer> begin, Node<Integer> end) {
        //判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end)
            return begin;
        //从第一个节点和第一个节点的后面一个几点
        //begin指向的是当前遍历到的最后一个<= nMidValue的节点
        Node<Integer> first = begin;
        Node<Integer> second = begin.next;

        int nMidValue = begin.val;
        //结束条件，second到最后了
        while (second != end.next && second != null) {//结束条件
            //一直往后寻找<=nMidValue的节点，然后与fir的后继节点交换
            if (second.val < nMidValue) {
                first = first.next;
                //判断一下，避免后面的数比第一个数小，不用换的局面
                if (first != second) {
                    int temp = first.val;
                    first.val = second.val;
                    second.val = temp;
                }
            }
            second = second.next;
        }
        //判断，有些情况是不用换的，提升性能
        if (begin != first) {
            int temp = begin.val;
            begin.val = first.val;
            first.val = temp;
        }
        //前部分递归
        quickSort(begin, first);
        //后部分递归
        quickSort(first.next, end);
        return begin;
    }

    public static Node insertSort(Node node) {



        return node;
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<Integer>(7);
        Node<Integer> node2 = new Node<Integer>(9);
        Node<Integer> node3 = new Node<Integer>(2);
        Node<Integer> node4 = new Node<Integer>(5);
        Node<Integer> node5 = new Node<Integer>(11);
        Node<Integer> node6 = new Node<Integer>(4);
        node1.next = node2;
        node2.pre = node1;
        node2.next = node3;
        node3.pre = node2;
        node3.next = node4;
        node4.pre = node3;
        node4.next = node5;
        node5.pre = node4;
        node5.next = node6;
        node6.pre = node5;
        //快速排序
    }
}
