package com.wjx.training.queuestacks;

import java.util.Stack;

/**
 * <h1><a href="https://leetcode.cn/problems/implement-queue-using-stacks/">232. 用栈实现队列</a></h1>
 * <p>
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * <p>
 * 实现 MyQueue 类：
 * <br>
 * <li>void push(int x) 将元素 x 推到队列的末尾</li>
 * <li>int pop() 从队列的开头移除并返回元素</li>
 * <li>int peek() 返回队列开头的元素</li>
 * <li>boolean empty() 如果队列为空，返回 true ；否则，返回 false</li>
 * 说明：
 * <li>你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。</li>
 * <li>你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。</li>
 *
 * <h2>示例 1:</h2>
 * 输入：<br>
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]<br>
 * [[], [1], [2], [], [], []]<br>
 * 输出：<br>
 * [null, null, null, 1, 1, false]<br>
 * <p>
 * 解释： <br>
 * MyQueue myQueue = new MyQueue(); <br>
 * myQueue.push(1); // queue is: [1] <br>
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue) <br>
 * myQueue.peek(); // return 1 <br>
 * myQueue.pop(); // return 1, queue is [2] <br>
 * myQueue.empty(); // return false <br>
 * <p>
 * 提示：<br>
 * <li>1 <= x <= 9</li>
 * <li>最多调用 100 次 push、pop、peek 和 empty</li>
 * <li>假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）</li>
 * <p>
 * 进阶：
 * <p>
 * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 *
 * @author Gin
 * @description
 * @date 2024/1/1 22:20
 */
public class ImplementQueueUsingStacks {
    class MyQueue {
        //栈的特点先进后出
        Stack<Integer> inStack;
        Stack<Integer> outStack;

        public MyQueue() {
            inStack = new Stack();
            outStack = new Stack();
        }

        //正常push
        public void push(int x) {
            inStack.push(x);
        }

        //输出的时候 要输出 先进栈的
        public int pop() {
            pushOutStack();
            return outStack.pop();
        }

        public int peek() {
            pushOutStack();
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        //方法将输入栈数据导入输出栈 输出栈即可作为先进先出队列
        private void pushOutStack() {
            if (!outStack.isEmpty()) return;
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
