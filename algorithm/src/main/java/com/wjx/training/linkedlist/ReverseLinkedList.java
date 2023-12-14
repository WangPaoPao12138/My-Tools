package com.wjx.training.linkedlist;

/**
 * <h1><a href="https://leetcode.cn/problems/reverse-linked-list/">206. 反转链表</a></h1>
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：head = [1,2,3,4,5]<br>
 * 输出：[5,4,3,2,1]<br>
 * <h2>示例 2:</h2>
 * 输入：head = [1,2]<br>
 * 输出：[2,1]<br>
 * <h2>示例 3</h2>
 * 输入：head = []<br>
 * 输出：[]<br>
 * <p>
 * 提示：<br>
 * <li>链表中节点的数目范围是 [0, 5000]</li>
 * <li>-5000 <= Node.val <= 5000</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/12 22:08
 */
public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //双指针
    public ListNode reverseList01(ListNode head) {
        ListNode temp = null;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            //记住下一个
            temp = cur.next;
            //指针反转
            cur.next = pre;
            //移动
            pre = cur;
            cur = temp;

        }
        return pre;
    }

    //递归  两两反转
    public ListNode reverseList02(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode pre, ListNode cur) {
        //反转当前节点
        if (cur == null) {
            return pre;
        }
        //记住下一个
        ListNode temp = cur.next;
        //反转指针
        cur.next = pre;
        //继续执行下翻转
        return reverse(cur, temp);
    }
}
