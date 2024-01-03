package com.wjx.training.queuestacks;

import java.util.Stack;

/**
 * <h1><a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation/">150. 逆波兰表达式求值</a></h1>
 * <p>
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * <br>
 * 注意：
 * <li>有效的算符为 '+'、'-'、'*' 和 '/' 。</li>
 * <li>每个操作数（运算对象）都可以是一个整数或者另一个表达式。</li>
 * <li>两个整数之间的除法总是 向零截断 。</li>
 * <li>表达式中不含除零运算。</li>
 * <li>输入是一个根据逆波兰表示法表示的算术表达式。</li>
 * <li>答案及所有中间计算结果可以用 32 位 整数表示。</li>
 * <h2>示例 1:</h2>
 * 输入：tokens = ["2","1","+","3","*"]<br>
 * 输出：9<br>
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9<br>
 * <h2>示例 2:</h2>
 * 输入：tokens = ["4","13","5","/","+"]<br>
 * 输出：6<br>
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6<br>
 * <h2>示例 2:</h2>
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]<br>
 * 输出：22<br>
 * 解释：该算式转化为常见的中缀算术表达式为：<br>
 * &nbsp;&nbsp;&nbsp;((10 * (6 / ((9 + 3) * -11))) + 17) + 5<br>
 * = ((10 * (6 / (12 * -11))) + 17) + 5<br>
 * = ((10 * (6 / -132)) + 17) + 5<br>
 * = ((10 * 0) + 17) + 5<br>
 * = (0 + 17) + 5<br>
 * = 17 + 5<br>
 * = 22<br>
 * <p>
 * 提示：<br>
 * <ul>
 * <li>1 <= tokens.length <= 10<sup>4</sup></li>
 * <li>tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数</li>
 * <p>
 * 逆波兰表达式：<br>
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。<br>
 * <ul>
 * <li>平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。</li>
 * <li>该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。</li>
 * </ul>
 * 逆波兰表达式主要有以下两个优点：<br>
 * <ul>
 * <li>去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。</li>
 * <li>适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中</li>
 * </ul>
 *
 * @author Gin
 * @description
 * @date 2024/1/2 13:32
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int beiChu = stack.pop();
                stack.push(stack.pop() / beiChu);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }
}
