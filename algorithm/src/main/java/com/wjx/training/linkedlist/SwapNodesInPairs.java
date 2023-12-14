package com.wjx.training.linkedlist;

/**
 * <h1><a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">24. 两两交换链表中的节点</a></h1>
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：head = [1,2,3,4]<br>
 * 输出：[2,1,4,3]<br>
 * <h2>示例 2:</h2>
 * 输入：head = []<br>
 * 输出：[]<br>
 * <h2>示例 3:</h2>
 * 输入：head = [1]<br>
 * 输出：[1]<br>
 * <p>
 * 提示：<br>
 * <li>链表中节点的数目在范围 [0, 100] 内</li>
 * <li>0 <= Node.val <= 100</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/12 22:48
 */
public class SwapNodesInPairs {
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
    //       cur next 难点在于 每组之间如何关联  用虚拟节点最好
    // xuni  1    2    3  4
    // xuni  2    1    3  4
    // xuni  2    1    4  3
    public ListNode swapPairs01(ListNode head) {
        ListNode dumyHead = new ListNode(-1,head);
        ListNode cur = dumyHead;//1
        while (cur.next != null && cur.next.next!=null){
            ListNode firstNode = cur.next;//f 1
            ListNode secondNode = firstNode.next;//s 2
            ListNode temp = secondNode.next;//3
            cur.next = secondNode;//虚拟节点指向 2
            secondNode.next = firstNode;//2->1 翻转
            firstNode.next = temp;//1-> 反转后指向下一组
            cur = firstNode;//1 当前下一组前一个节点为 1
        }
        return dumyHead.next;
    }
    //递归
    public ListNode swapPairs02(ListNode head) {
        // 退出提交
        if(head == null || head.next == null) return head;
        // 获取当前节点的下一个节点
        ListNode next = head.next;
        // 进行递归
        ListNode newNode = swapPairs02(next.next);
        // 这里进行交换
        next.next = head; //当前组进行交换
        head.next = newNode; //指向下一组

        return next;
    }
}
