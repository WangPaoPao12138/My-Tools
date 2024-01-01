package com.wjx.training.twopoint;

/**
 * <h1><a href="https://leetcode.cn/problems/reverse-string/">344. 反转字符串</a></h1>
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。<br>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。<br>
 * <h2>示例 1:</h2>
 * 输入：s = ["h","e","l","l","o"]<br>
 * 输出：["o","l","l","e","h"]<br>
 * <h2>示例 2:</h2>
 * 输入：s = ["H","a","n","n","a","h"]<br>
 * 输出：["h","a","n","n","a","H"]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= s.length <= 10<sup>5</sup></li>
 * <li>s[i] 都是 ASCII 码表中的可打印字符</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/26 17:21
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char temp = s[right];
            s[right] = s[left];
            s[left] = temp;
            left++;
            right--;
        }
    }
}
