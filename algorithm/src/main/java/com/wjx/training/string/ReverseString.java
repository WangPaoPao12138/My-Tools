package com.wjx.training.string;

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
 * @date 2023/12/20 20:25
 */
public class ReverseString {
    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString02(new char[]{'h', 'e', 'l', 'l', 'o'});
    }

    //错误的，不符合题意不是原地修改
    public void reverseString01(char[] s) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(s));
        s = stringBuilder.reverse().toString().toCharArray();
        System.out.println(s);
    }

    //双指针
    public void reverseString02(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }
    }
    //双指针思路2
    public void reverseString03(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r];  //构造 a ^ b 的结果，并放在 a 中
            s[r] ^= s[l];  //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
            s[l] ^= s[r];  //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
            l++;
            r--;
        }
    }
}
