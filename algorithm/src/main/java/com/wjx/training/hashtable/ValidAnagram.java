package com.wjx.training.hashtable;

import java.util.Arrays;

/**
 * <h1><a href="https://leetcode.cn/problems/valid-anagram/">242. 有效的字母异位词</a></h1>
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 <br>
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: s = "anagram", t = "nagaram"<br>
 * 输出: true<br>
 * <h2>示例 2:</h2>
 * 输入: s = "rat", t = "car"<br>
 * 输出: false<br>
 * <p>
 * 提示：<br>
 * <li>1 <= s.length, t.length <= 5 * 10<sup>4</sup></li>
 * <li>s 和 t 仅包含小写字母</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/17 19:01
 */
public class ValidAnagram {
    //哈希
    public boolean isAnagram01(String s, String t) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            counts[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }

    //排序
    public boolean isAnagram02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

}
