package com.wjx.training.array.additional;

import java.util.Stack;

/**
 * <h1><a href="https://leetcode.cn/problems/backspace-string-compare/description/">844. 比较含退格的字符串</a></h1>
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。<br>
 * 注意：如果对空文本输入退格字符，文本继续为空
 * <br>
 * <h2>示例 1:</h2>
 * 输入：s = "ab#c", t = "ad#c<br>
 * 输出: true<br>
 * 解释: s 和 t 都会变成 "ac"。<br>
 * <h2>示例 2:</h2>
 * 输入: s = "ab##", t = "c#d#"<br>
 * 输出: true<br>
 * 解释: s 和 t 都会变成 ""<br>
 * <h2>示例 3:</h2>
 * 输入：s = "a#c", t = "b"<br>
 * 输出：false<br>
 *    s 会变成 "c"，但 t 仍然是 "b"。<br>
 * <p>
 * 提示：<br>
 * <li>1 <= s.length, t.length <= 200</li>
 * <li>s 和 t 只含有小写字母以及字符 '#'</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/10 11:07
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        BackspaceStringCompare compare = new BackspaceStringCompare();
        compare.backspaceCompare01("ab##","c#d#");
    }
    //双指针 倒序查看数据
    public boolean backspaceCompare01(String s, String t) {
        int sIdx = s.length()-1;
        int tIdx = t.length()-1;
        int sPend = 0;
        int tPend = 0;
        while (sIdx >=0 || tIdx >= 0){
            //倒序找到第一个留存的位置
            while (sIdx >= 0) {
                char c = s.charAt(sIdx);
                if (c=='#'){
                    sPend++;
                }else {
                    if (sPend==0){
                        break;
                    }
                    sPend--;
                }
                sIdx--;
            }
            while (tIdx >=0){
                char c = t.charAt(tIdx);
                if (c=='#'){
                    tPend++;
                }else {
                    if (tPend==0){
                        break;
                    }
                    tPend--;
                }
                tIdx--;
            }
            if (sIdx == -1 || tIdx==-1  ){
                if (sIdx == tIdx) {
                    return true;
                }else {
                    return false;
                }
            }
            if (s.charAt(sIdx) != t.charAt(tIdx)){
                return false;
            }
            sIdx--;
            tIdx--;
        }
        return true;
    }

    //重构字符串
    public boolean backspaceCompare02(String s, String t) {
        return build(s).equals(build(t));
    }
    //重构字符串
    public String build(String str) {
        StringBuffer ret = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }

    //栈方法
    public boolean backspaceCompare03(String s, String t) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        //相当于将字符串重组
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#'  ) {
                if (stackS.size() != 0){
                    stackS.pop();
                }
            } else {
                stackS.push(s.charAt(i));
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#' ) {
                if (stackT.size() != 0){
                    stackT.pop();
                }
            } else {
                stackT.push(t.charAt(i));
            }
        }
        //比较结果
        if (stackS.size() != stackT.size()) {
            return false;
        } else {
            while (!stackS.isEmpty()){
                if (stackS.pop() != stackT.pop()) {
                    return false;
                }
            }
        }
        return true;
    }
}
