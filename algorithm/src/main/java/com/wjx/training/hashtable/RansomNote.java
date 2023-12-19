package com.wjx.training.hashtable;

/**
 * <h1><a href="https://leetcode.cn/problems/ransom-note/">383. 赎金信</a></h1>
 * <p>
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。<br>
 * 如果可以，返回 true ；否则返回 false 。<br>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。<br>
 * <h2>示例 1:</h2>
 * 输入：ransomNote = "a", magazine = "b"<br>
 * 输出：false<br>
 * <h2>示例 2:</h2>
 * 输入：ransomNote = "aa", magazine = "ab"<br>
 * 输出：false<br>
 * <h2>示例 3:</h2>
 * 输入：ransomNote = "aa", magazine = "aab"<br>
 * 输出：true<br>
 * <p>
 * 提示：<br>
 * <li>1 <= ransomNote.length, magazine.length <= 105</li>
 * <li>ransomNote 和 magazine 由小写英文字母组成</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/18 23:09
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ints = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            ints[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            ints[magazine.charAt(i) - 'a']--;
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] > 0) return false;
        }
        return true;
    }
}
