package com.zoco.LeetCode;

/**
 * 链表是否有环
 * 1、穷举遍历，最笨的方法
 * 2、哈希表缓存
 * 3、快慢指针：例子来形容：在一个环形跑道上，两个运动员在同一地点起跑，一个运动员速度快，一个运动员速度慢。当两人跑了一段时间，速度快的运动员必然会从速度慢的运动员身后再次追上并超过，原因很简单，因为跑道是环形的。
 * 4、Set集合大小变化；时间复杂度是O（N），空间复杂度上因为需要额外等数量的存储空间，所以空间复杂度是O（n）
 *
 * @author zoco
 * @creat 2019-12-02-17:52
 */
public class LinkedIsHasCricle {
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

    /**
     * 判断链表是否有环
     *
     * @param node
     * @return
     */
    public static boolean fastSlowPointer(Node node) {
        if (node == null || node.next == null) return false;
        Node fastN = node;
        Node slowN = node;
        while (true) {
            if (fastN.next == null) {
                return false;
            } else {
                if (fastN.next.next != null) {
                    fastN = fastN.next.next;
                    slowN = slowN.next;
                } else {
                    fastN = fastN.next;
                }
                if (fastN.equals(slowN)) return true;
            }
        }

    }

    /**
     * 移除环
     * 怎么找到环节点
     * https://blog.51cto.com/f1yinsky/2373303
     *
     * @param node
     * @return 返回尾节点
     */
    public static Node removeCricle(Node node) {
        Node fastNode = node;
        Node slowNode = node;
        while (fastNode != null) {
            if (fastNode.next != null) {
                fastNode = fastNode.next.next;
            } else {
                fastNode = null;
            }
            slowNode = slowNode.next;
            if (slowNode == fastNode) {
                fastNode = node;
                Node endNode = null;
                while (fastNode != slowNode) {
                    endNode = slowNode;
                    fastNode = fastNode.next;
                    slowNode = slowNode.next;
                }
                endNode.next = null;
                return node;
            }
        }
        return null;
    }

    /**
     * 计算环大小
     *
     * @param node
     * @return
     */
    public static int cricleSize(Node node) {
        int count = 0;
        Node fastNode = node;
        Node slowNode = node;
        while (fastNode != null) {
            if (fastNode.next != null) {
                fastNode = fastNode.next.next;
            } else {
                fastNode = null;
            }
            slowNode = slowNode.next;
            if (slowNode == fastNode) {
                fastNode = node;
                Node endNode = null;
                while (true) {
                    if (fastNode != slowNode) {
                        endNode = slowNode;
                        fastNode = fastNode.next;
                        slowNode = slowNode.next;

                    } else {
                        while (slowNode != endNode) {
                            slowNode = slowNode.next;
                            count++;
                        }
                        return count + 1;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 两个链表是否相交
     * 两个链表若相交最后一个节点必然相同
     * 直接法、利用计数、利用有环链表思路
     * 环链表：核心思想在于便利第一个链表使尾节点指向第二个链表的头结点，若第二个链表形成了环那么这两个链表必定相交
     *
     * 链表本身无环的情况
     *
     * @param link1
     * @param link2
     * @return
     */
    public static boolean IsIntersect(Node link1, Node link2) {
        if (link1 == null || link2 == null) return false;
        if (link1 == link2) return true;
        Node pnode;
        Node endNode;
        while (true) {
            endNode = link1;
            if (endNode.next != null) {
                endNode = endNode.next;
            } else {
                endNode.next = link2;
                break;
            }
        }
        return fastSlowPointer(link2);
    }

    public static void main(String[] args) {
        Node node5 = new Node<Integer>(5, null);
        Node node2 = new Node<Integer>(2, new Node<Integer>(3, new Node<Integer>(4, node5)));
        Node<Integer> node = new Node<>(1, node2);
        node5.next = node2;
       /* Node node2 = new Node<Integer>(2, null);
        Node<Integer> node = new Node<>(1, node2);
        node2.next = node;*/
        if (fastSlowPointer(node)) {
            System.out.println("此链表有环");
            System.out.println("环大小为：" + cricleSize(node));
            removeCricle(node);
            //System.out.println(node.item);
        } else {
            System.out.println("此链表五环");
        }
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }

    }
}
