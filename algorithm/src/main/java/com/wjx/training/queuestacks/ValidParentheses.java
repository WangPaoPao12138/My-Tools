package com.wjx.training.queuestacks;

import java.util.Stack;

/**
 * <h1><a href="https://leetcode.cn/problems/valid-parentheses/">20. 有效的括号</a></h1>
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <ol>
 *     <li>左括号必须用相同类型的右括号闭合。</li>
 *     <li>左括号必须以正确的顺序闭合。</li>
 *     <li>每个右括号都有一个对应的相同类型的左括号。</li>
 * </ol>
 *  <br>
 * <h2>示例 1:</h2>
 * 输入：s = "()"<br>
 * 输出：true<br>
 * <h2>示例 2:</h2>
 * 输入：s = "()[]{}"<br>
 * 输出：true<br>
 * <h2>示例 3:</h2>
 * 输入：s = "(]"<br>
 * 输出：false<br>
 * <p>
 * 提示：<br>
 * <li>1 <= s.length <= 10<sup>4</sup></li>
 * <li>s 仅由括号 '()[]{}' 组成</li>
 *
 * @author Gin
 * @description
 * @date 2024/1/2 11:34
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            //左括号
            char c = s.charAt(i);
            if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.peek()) {
                return false;
            }else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
