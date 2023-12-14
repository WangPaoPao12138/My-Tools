package com.wjx.training.linkedlist;

/**
 * <h1><a href="https://leetcode.cn/problems/remove-linked-list-elements/">203. 移除链表元素</a></h1>
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <br>
 * <h2>示例 1:</h2>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/removelinked-list.jpg" style="width: 500px; height: 142px;">
 * 输入：head = [1,2,6,3,4,5,6], val = 6<br>
 * 输出：[1,2,3,4,5]<br>
 * <h2>示例 2:</h2>
 * 输入：head = [], val = 1<br>
 * 输出：[]<br>
 * <h2>示例 3:</h2>
 * 输入：head = [7,7,7,7], val = 7<br>
 * 输出：[]<br>
 * <p>
 * 提示：<br>
 * <li>列表中的节点数目在范围 [0, 10<sup>4</sup>] 内</li>
 * <li>1 <= Node.val <= 50</li>
 * <li>0 <= val <= 50</li>
 *
 * <h1>核心思想是头节点单独处理 无论是单独处理原表头节点还是虚拟头节点 其余正常处理</h1>
 *
 * @author Gin
 * @description
 * @date 2023/12/12 18:23
 */
public class RemoveLinkedListElements {
    static class ListNode {
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

    public static void main(String[] args) {
        RemoveLinkedListElements elements = new RemoveLinkedListElements();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,
                new ListNode(5, new ListNode(6, null))))));
        elements.removeElements02(listNode, 6);
    }

    /**
     * 原链表操作,不添加虚拟节点and pre Node方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements01(ListNode head, int val) {
        //将头节点 符合条件或者null的删除
        while (head != null && head.val == val) {
            head = head.next;
        }
        //cur为符合条件的节点
        ListNode cur = head;
        while (cur != null) {
            //找到下一个符合条件的节点
            while (cur.next != null && cur.next.val == val) {
                //符合条件删除节点
                cur.next = cur.next.next;
            }
            //移动
            cur = cur.next;
        }
        return head;
    }

    /**
     * 添加虚节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 123456 6
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements02(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        //虚拟节点 - 结果
        ListNode dummy = new ListNode(-1, head);
        //pre为符合条件的节点
        ListNode pre = dummy;
        //cur当前判断条件
        ListNode cur = head;
        while (cur != null) {
            //不符合则将pre和下一个节点连接
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                //pre为符合条件节点
                pre = cur;
            }
            //cur判断下一个
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 不添加虚节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 123456 6
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements03(ListNode head, int val) {
        //遍历找到第不为空且不相等的头节点
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        //pre为符合条件的节点
        ListNode pre = head;
        //cur当前判断条件
        ListNode cur = head.next;
        while (cur != null) {
            //不符合则将pre和下一个节点连接
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                //pre为符合条件节点
                pre = cur;
            }
            //cur判断下一个
            cur = cur.next;
        }
        return head;
    }
}
