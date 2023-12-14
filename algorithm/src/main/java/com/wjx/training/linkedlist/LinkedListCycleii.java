package com.wjx.training.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1><a href="https://leetcode.cn/problems/linked-list-cycle-ii//">142. 环形链表 II</a></h1>
 * <p>
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <br>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。<br>
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。<br>
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。。<br>
 * <br>
 * <h2>示例 1:</h2>
 * <img src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png"><br>
 * 输入：head = [3,2,0,-4], pos = 1<br>
 * 输出：返回索引为 1 的链表节点<br>
 * 解释：链表中有一个环，其尾部连接到第二个节点。<br>
 * <h2>示例 2:</h2>
 * 输入：head = [1,2], pos = 0<br>
 * 输出：返回索引为 0 的链表节点<br>
 * 解释：链表中有一个环，其尾部连接到第一个节点。<br>
 * <p>
 * <h2>示例 3:</h2>
 * 输入：head = [1], pos = -1<br>
 * 输出：返回 null<br>
 * 解释：链表中没有环。<br>
 * 提示：<br>
 * <li>链表中节点的数目范围在范围 [0, 104] 内</li>
 * <li>-105 <= Node.val <= 105</li>
 * <li>pos 的值为 -1 或者链表中的一个有效索引</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/14 21:34
 */
public class LinkedListCycleii {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //双指针
    //slow走a和fast相遇  fast走2a 并且2a其中为n
    //slow 从相遇点开始走 和head的一个点一起走则一定是在环入口相遇
    public ListNode detectCycle01(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    //hash表
    public ListNode detectCycle02(ListNode head) {
        ListNode temp = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            } else {
                visited.add(temp);
            }
            temp = temp.next;
        }
        return null;
    }
}
