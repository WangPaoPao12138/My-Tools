package com.wjx.training.linkedlist;

import javax.xml.soap.Node;

/**
 * <h1><a href="https://leetcode.cn/problems/design-linked-list/">707. 设计链表</a></h1>
 * <p>
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。<br>
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。<br>
 * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。<br>
 * <h2>实现 MyLinkedList 类：</h2>
 * <li>MyLinkedList() 初始化 MyLinkedList 对象。</li>
 * <li>int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。</li>
 * <li>void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。</li>
 * <li>void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。</li>
 * <li>void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
 * 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。</li>
 * <li>void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。</li>
 * <h2>示例 :</h2>
 * 输入：<br>
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]<br>
 * [[], [1], [3], [1, 2], [1], [1], [1]]<br>
 * 输出：<br>
 * [null, null, null, null, 2, null, 3]<br>
 * <p>
 * 解释：<br>
 * MyLinkedList myLinkedList = new MyLinkedList();<br>
 * myLinkedList.addAtHead(1);<br>
 * myLinkedList.addAtTail(3);<br>
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3<br>
 * myLinkedList.get(1);              // 返回 2<br>
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3<br>
 * myLinkedList.get(1);              // 返回 3<br>
 * <li>0 <= index, val <= 1000</li>
 * <li>请不要使用内置的 LinkedList 库。</li>
 * <li>调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/12 18:58
 */
public class DesignLinkedList {
    //单链表
    class MyLinkedList01 {
        class ListNode01 {
            int val;
            ListNode01 next;

            ListNode01() {
            }

            ListNode01(int val) {
                this.val = val;
            }

            ListNode01(int val, ListNode01 next) {
                this.val = val;
                this.next = next;
            }
        }

        ListNode01 head;
        int size;

        public MyLinkedList01() {
            size = 0;
            //虚拟节点
            head = new ListNode01(-1);
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            //当前遍历节点 符合条件
            ListNode01 cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }
            //先给头虚拟节点
            ListNode01 pre = head;
            for (int i = 0; i < index; i++) {
                //从idx0 开始赋值 到idx-1停止
                pre = pre.next;
            }
            ListNode01 listNode = new ListNode01(val, pre.next);
            pre.next = listNode;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            //先给头虚拟节点
            ListNode01 pre = head;
            for (int i = 0; i < index; i++) {
                //从idx0 开始赋值 到idx-1停止
                pre = pre.next;
            }
            //idx-1 的 next 为idx的next  idx删除
            pre.next = pre.next.next;
            size--;
        }

        public ListNode01 getNode(int index) {
            //当前遍历节点 符合条件
            ListNode01 cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur;
        }
    }

    //双向链表
    class MyLinkedList {

        class ListNode02 {
            int val;
            ListNode02 pre;
            ListNode02 next;

            ListNode02() {
            }

            ListNode02(int val) {
                this.val = val;
            }

            ListNode02(int val, ListNode02 pre, ListNode02 next) {
                this.val = val;
                this.pre = pre;
                this.next = next;
            }
        }

        int size;
        ListNode02 head, tail;

        public MyLinkedList() {
            size = 0;
            //虚拟节点
            head = new ListNode02(-1);
            tail = new ListNode02(-1);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode02 cur = head;
            if (index > size / 2) {//head 0 1 2 3 4 tail  i 5 idx4
                cur = tail;
                for (int i = size; i >= index; i--) {
                    cur = cur.pre;
                }
            } else {
                for (int i = 0; i <= index; i++) {
                    cur = cur.next;
                }
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            //等价于在第0个元素前添加
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            //等价于在第0个元素前添加
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
//            if (index > size || index < 0) {
//                return;
//            }
            //index大于链表长度
            if (index > size) {
                return;
            }
            //index小于0
            if (index < 0) {
                index = 0;
            }
            ListNode02 cur = getNode(index);
            ListNode02 pre = cur.pre;
            ListNode02 node02 = new ListNode02(val);
            pre.next = node02;
            node02.pre = pre;
            node02.next = cur;
            cur.pre = node02;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) {
                return;
            }
            ListNode02 cur = getNode(index);
            ListNode02 pre = cur.pre;
            ListNode02 next = cur.next;
            pre.next = next;
            next.pre = pre;
            size--;
        }

        public ListNode02 getNode(int index) {
            if (index < 0 || index >= size) {
                return null;
            }
            ListNode02 cur = head;
            if (index > size / 2) {//head 0 1 2 3 4 tail  i 5 idx4
                cur = tail;
                for (int i = size; i > index; i--) {
                    cur = cur.pre;
                }
            } else {
                for (int i = 0; i < index; i++) {
                    cur = cur.next;
                }
            }
            return cur;
        }
    }

}
