package com.wjx.training.linkedlist;

/**
 * <h1><a href="https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/">面试题 02.07. 链表相交</a></h1>
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <br>
 * 图示两个链表在节点 c1 开始相交：
 * <br>
 * <img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" style="height: 130px; width: 400px;">
 * <br>题目数据 保证 整个链式结构中不存在环。
 * <br>注意，函数返回结果后，链表必须 保持其原始结构 。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3<br>
 * 输出：Intersected at '8'<br>
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。<br>
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。<br>
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。<br>
 * <h2>示例 2:</h2>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1<br>
 * 输出：Intersected at '2'<br>
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。<br>
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。<br>
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。<br>
 * <h2>示例 3:</h2>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2<br>
 * 输出：null<br>
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。<br>
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。<br>
 * 这两个链表不相交，因此返回 null 。<br>
 * 提示：<br>
 * <li>listA 中节点数目为 m</li>
 * <li>listB 中节点数目为 n</li>
 * <li>0 <= m, n <= 3 * 104</li>
 * <li>1 <= Node.val <= 105</li>
 * <li>0 <= skipA <= m</li>
 * <li>0 <= skipB <= n</li>
 * <li>如果 listA 和 listB 没有交点，intersectVal 为 0</li>
 * <li>如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/14 0:10
 */
public class IntersectionOfTwoLinkedListsLcci {
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

    public ListNode getIntersectionNode01(ListNode headA, ListNode headB) {
        ListNode h1 = headA, h2 = headB;

        while (h1 != h2) {

            h1 = h1 == null ? headB : h1.next;
            h2 = h2 == null ? headA : h2.next;
        }

        return h1;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;
        while (curA != null) {//A长
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {//B长
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        int abs = Math.abs(lenA - lenB);
        //可以抽方法 将A，B尾部对齐 也是将指针对齐
        if (lenA > lenB) {
            while (abs > 0) {
                curA = curA.next;
                abs--;
            }
        } else if (lenB > lenA) {
            while (abs > 0) {
                curB = curB.next;
                abs--;
            }
        }
        //遍历
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;

    }
}
