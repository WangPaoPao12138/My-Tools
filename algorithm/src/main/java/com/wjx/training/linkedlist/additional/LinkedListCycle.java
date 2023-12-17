package com.wjx.training.linkedlist.additional;

import java.util.HashSet;

/**
 * <h1><a href="https://leetcode.cn/problems/linked-list-cycle/">141. 环形链表</a></h1>
 * <p>
 * 给你一个链表的头节点 head ，判断链表中是否有环
 * <br>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。<br>
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。<br>
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。<br>
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。<br>
 * <br>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：head = [3,2,0,-4], pos = 1<br>
 * 输出：true<br>
 * 解释：链表中有一个环，其尾部连接到第二个节点。<br>
 * <h2>示例 2:</h2>
 * 输入：head = [1,2], pos = 0<br>
 * 输出：true<br>
 * 解释：链表中有一个环，其尾部连接到第一个节点。<br>
 * <h2>示例 3:</h2>
 * 输入：head = [1], pos = -1<br>
 * 输出：false<br>
 * 解释：链表中没有环。<br>
 * <p>
 * 提示：<br>
 * <li>链表中节点的数目范围是 [0, 104]</li>
 * <li>-105 <= Node.val <= 105</li>
 * <li>pos 为 -1 或者链表中的一个 有效索引 。</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/14 22:11
 */
public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    //双指针
    //只要有环那么会一直循环知道 快慢指针相遇
    //怎样找到相遇节点？
    public boolean hasCycle01(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    //Hash
    public boolean hasCycle02(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            if (visited.contains(temp)){
                return true;
            }else {
                visited.add(temp);
            }
            temp = temp.next;
        }
        return false;
    }
}
