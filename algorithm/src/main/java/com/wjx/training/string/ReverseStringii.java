package com.wjx.training.string;

/**
 * <h1><a href="https://leetcode.cn/problems/reverse-string-ii/">541. 反转字符串 II</a></h1>
 * <p>
 * <p>
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。<br>
 * <li>如果剩余字符少于 k 个，则将剩余字符全部反转。</li>
 * <li>如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。</li>
 * <h2>示例 1:</h2>
 * 输入：s = "abcdefg", k = 2<br>
 * 输出："bacdfeg"<br>
 * <h2>示例 2:</h2>
 * 输入：s = "abcd", k = 2<br>
 * 输出："bacd"<br>
 * <p>
 * 提示：<br>
 * <li>1 <= s.length <= 10<sup>4</sup></li>
 * <li>s 仅由小写英文组成</li>
 * <li>1 <= k <= 10<sup>4</sup></li>
 *
 * @author Gin
 * @description
 * @date 2023/12/20 20:11
 */
public class ReverseStringii {
    public static void main(String[] args) {
        ReverseStringii reverseString = new ReverseStringii();
        reverseString.reverseStr01("abcdefg", 2);
    }

    //解法1 数组
    public String reverseStr01(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i = i + 2 * k) {
            int left = i;
            int right = Math.min(chars.length - 1, i + k - 1);
            while (left < right) {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    //解法2 StringBuffer reserve函数
    public String reverseStr02(String s, int k) {
        StringBuffer res = new StringBuffer();
        int length = s.length();
        int start = 0;
        while (start < length) {
            // 找到k处和2k处
            StringBuffer temp = new StringBuffer();
            // 与length进行判断，如果大于length了，那就将其置为length
            int firstK = (start + k > length) ? length : start + k;
            int secondK = (start + (2 * k) > length) ? length : start + (2 * k);

            //无论start所处位置，至少会反转一次
            temp.append(s.substring(start, firstK));
            res.append(temp.reverse());

            // 如果firstK到secondK之间有元素，这些元素直接放入res里即可。
            if (firstK < secondK) { //此时剩余长度一定大于k。
                res.append(s.substring(firstK, secondK));
            }
            start += (2 * k);
        }
        return res.toString();
    }
}
