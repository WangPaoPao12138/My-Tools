package com.wjx.training.string;

import java.util.Arrays;

/**
 * <h1><a href="https://leetcode.cn/problems/repeated-substring-pattern/">459. 重复的子字符串</a></h1>
 * <p>
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: s = "abab"<br>
 * 输出: true<br>
 * 解释: 可由子串 "ab" 重复两次构成。<br>
 * <h2>示例 2:</h2>
 * 输入: s = "aba"<br>
 * 输出: false<br>
 * <h2>示例 3:</h2>
 * 输入: s = "abcabcabcabc"<br>
 * 输出: true<br>
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)<br>
 * <p>
 * 提示：<br>
 * <li>11 <= s.length <= 10<sup>4</sup></li>
 * <li>1s 由小写英文字母组成</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/23 0:40
 */
public class RepeatedSubstringPattern {
    //暴力
    public boolean repeatedSubstringPattern01(String s) {
        for (int i = 1; i * 2 <= s.length(); i++) {
            if (s.length() % i == 0) {
                boolean match = true;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    //与运算 TODO
    public boolean repeatedSubstringPattern02(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }


    //KMP TODO
    public boolean repeatedSubstringPattern03(String s) {
        return kmp03(s + s, s);
    }

    public boolean kmp03(String query, String pattern) {
        int n = query.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int match = -1;
        for (int i = 1; i < n - 1; ++i) {
            while (match != -1 && pattern.charAt(match + 1) != query.charAt(i)) {
                match = fail[match];
            }
            if (pattern.charAt(match + 1) == query.charAt(i)) {
                ++match;
                if (match == m - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern repeatedSubstringPattern = new RepeatedSubstringPattern();
        repeatedSubstringPattern.repeatedSubstringPattern03("abab");
    }
    //KMP 自己
    public boolean repeatedSubstringPattern04(String s) {
        int m = s.length();
        //前缀数组
        int[] next = getNext04(s);
        int match =0;
        //
        if (next[s.length()-1] > 0 && s.length() % (s.length() - next[s.length()-1]) == 0) {
            return true;
        }
        return false;
    }
    public int[] getNext04(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;//最长相等前后缀的 前缀末尾
        next[0] = 0;
        //i 是后缀末尾
        for (int i = 1; i < next.length; i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                //找到上一个位置的最小相等前后缀
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i]=j;
        }
        return next;
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s.equals("")) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }
}
