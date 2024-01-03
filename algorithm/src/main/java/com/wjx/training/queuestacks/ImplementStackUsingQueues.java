package com.wjx.training.queuestacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <h1><a href="https://leetcode.cn/problems/implement-stack-using-queues/">225. 用队列实现栈</a></h1>
 * <p>
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * <p>
 * 实现 MyStack 类：
 * <p>
 * <li>void push(int x) 将元素 x 压入栈顶。</li>
 * <li>int pop() 移除并返回栈顶元素。</li>
 * <li>int top() 返回栈顶元素。</li>
 * <li>boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。</li>
 * <br>
 * 注意：
 * <p>
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * <h2>示例 1:</h2>
 * 输入：<br>
 * ["MyStack", "push", "push", "top", "pop", "empty"]<br>
 * [[], [1], [2], [], [], []]<br>
 * 输出：<br>
 * [null, null, null, 2, 2, false]<br>
 * 解释：
 * <p>
 * MyStack myStack = new MyStack();<p>
 * myStack.push(1);<p>
 * myStack.push(2);<p>
 * myStack.top(); // 返回 2<p>
 * myStack.pop(); // 返回 2<p>
 * myStack.empty(); // 返回 False<p>
 * 提示：<br>
 * <li>1 <= x <= 9</li>
 * <li>最多调用100 次 push、pop、top 和 empty</li>
 * <li>每次调用 pop 和 top 都保证栈不为空</li>
 *
 * @author Gin
 * @description
 * @date 2024/1/1 22:33
 */
public class ImplementStackUsingQueues {
    class MyStack {
        //操作队列
        Queue<Integer> q1;
        //辅助队列
        Queue<Integer> q2;

        public MyStack() {
            q1 = new LinkedList();
            q2 = new LinkedList();
        }

        //将数据存进q2保证新进的数据在q2上
        public void push(int x) {
            q2.offer(x);
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
            Queue tempQ = q1;
            q1 = q2;
            q2 = tempQ;
        }

        //        方法2同理 不同queue实现
//        //q1作为主要的队列，其元素排列顺序和出栈顺序相同
//        Queue<Integer> q1 = new ArrayDeque<>();
//        //q2仅作为临时放置
//        Queue<Integer> q2 = new ArrayDeque<>();
//
//        public MyStack() {
//
//        }
//        //在加入元素时先将q1中的元素依次出栈压入q2，然后将新加入的元素压入q1，再将q2中的元素依次出栈压入q1
//        public void push(int x) {
//            while (q1.size() > 0) {
//                q2.add(q1.poll());
//            }
//            q1.add(x);
//            while (q2.size() > 0) {
//                q1.add(q2.poll());
//            }
//        }
        public int pop() {
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    class MyStack01 {
        // Deque 接口继承了 Queue 接口
        // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
        Deque<Integer> que1; // 和栈中保持一样元素的队列
        Deque<Integer> que2; // 辅助队列

        public MyStack01() {
            que1 = new ArrayDeque<>();
            que2 = new ArrayDeque<>();
        }

        public void push(int x) {
            que1.addLast(x);
        }

        public int pop() {
            int size = que1.size();
            // 将 que1 导入 que2 ，但留下最后一个值
            size--;
            while (size-- > 0) {
                que2.addLast(que1.peekFirst());
                que1.pollFirst();
            }
            int res = que1.pollFirst();
            // 将 que2 对象的引用赋给了 que1 ，此时 que1，que2 指向同一个队列
            que1 = que2;
            // 如果直接操作 que2，que1 也会受到影响，所以为 que2 分配一个新的空间
            que2 = new ArrayDeque<>();
            return res;
        }

        public int top() {
            return que1.peekLast();
        }

        public boolean empty() {
            return que1.isEmpty();
        }
    }

    class MyStack03 {
        // Deque 接口继承了 Queue 接口
        // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
        Deque<Integer> que1;

        public MyStack03() {
            que1 = new ArrayDeque<>();
        }

        public void push(int x) {
            que1.addLast(x);
        }

        public int pop() {
            int size = que1.size();
            size--;
            // 将 que1 导入 que2 ，但留下最后一个值
            while (size-- > 0) {
                que1.addLast(que1.peekFirst());
                que1.pollFirst();
            }

            int res = que1.pollFirst();
            return res;
        }

        //        双端队列。。
//        public int pop() {
//            return que1.pollLast();
//        }
        public int top() {
            return que1.peekLast();
        }

        public boolean empty() {
            return que1.isEmpty();
        }
    }

    class MyStack04 {

        Queue<Integer> queue;

        public MyStack04() {
            queue = new LinkedList<>();
        }

        //每 offer 一个数（A）进来，都重新排列，把这个数（A）放到队列的队首
        public void push(int x) {
            int size = queue.size();
            queue.offer(x);
            //移动除了 A 的其它数
            while (size-- > 0)
                //旧的队列移出重新塞进队列这样新进的数据能放进队首
                queue.offer(queue.poll());
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
