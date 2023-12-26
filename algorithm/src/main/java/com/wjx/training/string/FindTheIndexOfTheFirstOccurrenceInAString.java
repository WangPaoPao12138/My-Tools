package com.wjx.training.string;

/**
 * <h1><a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/">28. 找出字符串中第一个匹配项的下标</a></h1>
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * <br>如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：haystack = "sadbutsad", needle = "sad"<br>
 * 输出：0<br>
 * 解释："sad" 在下标 0 和 6 处匹配。<br>
 * <h2>示例 2:</h2>
 * 输入：haystack = "leetcode", needle = "leeto"<br>
 * 输出：-1<br>
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。<br>
 * <p>
 * 提示：<br>
 * <li>1 <= haystack.length, needle.length <= 10<sup>4</sup></li>
 * <li>haystack 和 needle 仅由小写英文字符组成</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/21 23:06
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {
    //暴力
    public int strStr01(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    //滑动窗口
    /**
     * 基于窗口滑动的算法
     * <p>
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(1)
     * 注：n为haystack的长度，m为needle的长度
     */
    public int strStr02(String haystack, String needle) {
        int result = -1;
        int hLen = haystack.length();
        int nLen = needle.length();
        //都为0
        if (nLen == 0) return 0;
        if (hLen < nLen) return result;
        //haystack 串找起始位置
        int left = 0;
        //needle 串起始位置 和  haystack同步
        int right = 0;
        while (left <= hLen - nLen) {
            //窗口找到头
            while (left <= hLen - nLen && haystack.charAt(left) != needle.charAt(0)) {
                left++;
            }
            if (left > hLen - nLen) return result;
            left++;
            right++;
            while (right < nLen && haystack.charAt(left) == needle.charAt(right)) {
                left++;
                right++;
            }
            if (right == nLen) {
                result = left - right;
                break;
            } else {
                left = left - right + 1;
                right = 0;
            }
        }
        return result;
    }

    //KMP - 1 todo
    public void getNext03(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }

            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }

    public int strStr03(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        //前缀表
        int[] next = new int[needle.length()];
        getNext03(next, needle);
        //
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return (i - needle.length() + 1);
            }
        }

        return -1;
    }

    //KMP不减一
    //前缀表（不减一）Java实现 todo

    /**
     *
     * @param haystack 文本串/主串
     * @param needle 模式串
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] next = new int[needle.length()];
        getNext(next, needle);
        //模式串 的下标
        int j = 0;
        //文本串下标
        for (int i = 0; i < haystack.length(); i++) {
            //寻找j的相等的位置
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = next[j - 1];
            //i和j相等
            if (needle.charAt(j) == haystack.charAt(i))
                j++;
            if (j == needle.length())
                return i - needle.length() + 1;
        }
        return -1;

    }

    //寻找最长相等前后缀
    private void getNext(int[] next, String s) {
        int j = 0;//前缀末尾
        next[0] = 0;
        //后缀末尾
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = next[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }
}
