package com.wjx.training.linkedlist;

/**
 * <h1><a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">19. 删除链表的倒数第 N 个结点</a></h1>
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <br>
 * <h2>示例 1:</h2>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg" style="width: 542px; height: 222px;"><br>
 * 输入：head = [1,2,3,4,5], n = 2<br>
 * 输出：[1,2,3,5]<br>
 * <h2>示例 2:</h2>
 * 输入：head = [1], n = 1<br>
 * 输出：[]<br>
 * <h2>示例 3:</h2>
 * 输入：head = [1,2], n = 1<br>
 * 输出：[1]<br>
 * <p>
 * 提示：<br>
 * <li>链表中结点的数目为 sz</li>
 * <li>1 <= sz <= 30</li>
 * <li>0 <= Node.val <= 100</li>
 * <li>1 <= n <= sz</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/13 23:44
 */
public class RemoveNthNodeFromEndOfList {
    class ListNode {
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
    public ListNode removeNthFromEnd01(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        //left得到倒数n+1的位置才好操作删除
        ListNode left = dummy;
        //right
        ListNode right = dummy;
        //单向链表  需要执行到倒数n+1的才好删除n
        //所以right 若多走一步 则一起移动需要判断right本身
        //否则 则一起移动需要判断right.next
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;

        return dummy.next;
    }

    public ListNode removeNthFromEnd02(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        //left得到倒数n+1的位置才好操作删除
        ListNode left = dummy;
        //right
        ListNode right = dummy;
        //单向链表  需要执行到倒数n+1的才好删除n
        //所以right 若多走一步 则一起移动需要判断right本身
        //否则 则一起移动需要判断right.next
        for (int i = 0; i <= n; i++) {
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;

        return dummy.next;
    }
}
