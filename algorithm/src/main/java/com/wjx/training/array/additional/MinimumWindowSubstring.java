package com.wjx.training.array.additional;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <h1><a href="https://leetcode.cn/problems/minimum-window-substring/">76. 最小覆盖子串</a></h1>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。。
 * <br>
 * 注意
 * <li>对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。</li>
 * <li>如果 s 中存在这样的子串，我们保证它是唯一的答案。</li>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：s = "ADOBECODEBANC", t = "ABC"<br>
 * 输出："BANC"<br>
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。<br>
 * <h2>示例 2:</h2>
 * 输入：s = "a", t = "a"<br>
 * 输出："a"<br>
 * 解释：整个字符串 s 是最小覆盖子串。<br>
 * <p>
 * 提示：<br>
 * <li>m == s.length</li>
 * <li>n == t.length</li>
 * <li>1 <= m, n <= 10<sup>5</sup></li>
 * <li>s 和 t 由英文字母组成</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/10 20:51
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring substring = new MinimumWindowSubstring();
        substring.minWindow01("ADOBECODEBANC", "ABC");
    }

    //双指针
    public String minWindow01(String s, String t) {
        if (s.length() < t.length()) return "";
        int[] target = new int['z' - 'A' + 1];
        for (char c : t.toCharArray()) {
            target[c - 'A']++;
        }
        int left = 0;//窗口左
        int right;//窗口右
        int begin = 0;//最小字符串开始
        int count = t.length();//目标字符串长度
        int resultSize = Integer.MAX_VALUE;//结果最小字符串长度
        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            //找到对应需要的字符 则--  表明窗口种需要加入的结果字符数量
            if (target[c - 'A'] > 0) {
                count--;
            }
            target[c - 'A']--; //加入窗口
            if (count == 0) {//表明已经将所有所需字符串加入结果字符
                //可能是新窗口所以
                //释放不需要的数字
                while (left < right && target[s.charAt(left) - 'A'] < 0) {
                    target[s.charAt(left) - 'A']++;
                    left++;
                }
                //找最小位置 比较结果
                if (right - left + 1 < resultSize) {
                    begin = left;
                    resultSize = right - left + 1;
                }
                //释放最左侧字符 重新找窗口
                target[s.charAt(left) - 'A']++;
                left++;
                count++;
            }
        }
        return resultSize == Integer.MAX_VALUE ? "" : s.substring(begin, begin + resultSize);
    }

    Map<Character, Integer> tMap = new HashMap<Character, Integer>();
    Map<Character, Integer> windowMap = new HashMap<Character, Integer>();

    //最小窗口
    public String minWindow02(String s, String t) {
        int tLen = t.length();
        //放进对比map
        for (int i = 0; i < tLen; i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        //窗口左右界限
        int left = 0, right = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (right < sLen) {
            right++;
            //查看目标中是否有当前的值
            if (right<sLen && tMap.containsKey(s.charAt(right))){
                windowMap.put(s.charAt(right),windowMap.getOrDefault(s.charAt(right),0)+1);
            }
            //当 window比 t大并且窗口还在 左边缩进
            while (check() && left <= right) {

                if (right - left + 1 < len) {
                    len = right - left + 1;
                    ansL = left;
                    ansR = left + len;
                }
                //原来的t中包含则 减一
                if (tMap.containsKey(s.charAt(left))) {
                    windowMap.put(s.charAt(left), windowMap.getOrDefault(s.charAt(left), 0) - 1);
                }
                ++left;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }
    //查看window结果数量是否小于t中 小于就false  大于等于就是true
    public boolean check() {
        Iterator iter = tMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (windowMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
}
