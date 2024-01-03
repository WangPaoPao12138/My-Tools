package com.wjx.training.queuestacks;

import java.util.Stack;

/**
 * <h1><a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/">1047. 删除字符串中的所有相邻重复项</a></h1>
 * <p>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。<br>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。<br>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。<br>
 * <br>
 * <h2>示例 1:</h2>
 * 输入："abbaca"<br>
 * 输出："ca"<br>
 * 解释：<br>
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，<br>
 * 这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，<br>
 * 其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。<br>
 * <p>
 * 提示：<br>
 * <ol>
 * <li>1 <= S.length <= 20000</li>
 * <li>S 仅由小写英文字母组成。</li>
 * </ol>
 *
 * @author Gin
 * @description
 * @date 2024/1/2 13:07
 */
public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        Stack<Character> characterStack = new Stack<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (!characterStack.isEmpty() && characterStack.peek() == c) {
                characterStack.pop();
                continue;
            }
            characterStack.push(c);
        }
        String result = "";
        while (!characterStack.isEmpty()) {
            result = characterStack.pop() + result;
        }
        return result;
    }
}
