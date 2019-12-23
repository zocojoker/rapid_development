package com.zoco.LeetCode;

/**
 * 链表反转
 *
 * @author zoco
 * @creat 2019-11-03-13:48
 */
public class LinkedRevers {
    static class Node<E> {
        E item;
        Node<E> next;
        //LinkedRevers.Node<E> prev;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
            //this.prev = prev;
        }
    }

    public static Node revers(Node node) {
        if (node.next == null)
            return node;
        Node nodePrev = null;
        Node nodeNext;
        Node cunrrent = node;
        while (true) {
            nodeNext = cunrrent.next;
            if (nodePrev != null)
                cunrrent.next = nodePrev;
            else cunrrent.next = null;
            nodePrev = cunrrent;
            if (nodeNext == null) break;
            cunrrent = nodeNext;
        }
        //cunrrent.next = nodePrev;
        return cunrrent;
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1, new Node<Integer>(2, new Node<Integer>(3, null)));
        Node n = revers(node);
        while (n != null) {
            System.out.println(n.item);
            n = n.next;
        }

    }
}

