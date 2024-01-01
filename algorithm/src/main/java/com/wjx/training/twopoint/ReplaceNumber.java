package com.wjx.training.twopoint;

import java.util.Scanner;

/**
 * <h1><a href="https://kamacoder.com/problempage.php?pid=1064">54. 替换数字（第八期模拟笔试）</a></h1>
 * <p>
 *     <div class="p-4 overflow-auto">
 *                             <div class="fs-4 fw-semibold mb-2">
 *                                 54. 替换数字（第八期模拟笔试）                            </div>
 *
 *                             <div class="small-font text-body-secondary">
 *                                 时间限制：1.000S&nbsp;&nbsp;空间限制：128MB
 *                             </div>
 *
 *                             <div class="mt-3">
 *                                 <h6 class="h6">题目描述</h6>
 *                                 <div class="quote">
 *                                     给定一个字符串 s，它包含小写字母和数字字符，请编写一个函数，将字符串中的字母字符保持不变，而将每个数字字符替换为number。
 * <p>
 * 例如，对于输入字符串 "a1b2c3"，函数应该将其转换为 "anumberbnumbercnumber"。                                </div>
 *                             </div>
 *
 *                             <div class="mt-3">
 *                                 <h6 class="h6">输入描述</h6>
 *                                 <div class="quote">
 *                                     输入一个字符串 s,s 仅包含小写字母和数字字符。                                </div>
 *                             </div>
 *
 *                             <div class="mt-3">
 *                                 <h6 class="h6">输出描述</h6>
 *                                 <div class="quote">
 *                                     打印一个新的字符串，其中每个数字字符都被替换为了number                                </div>
 *                             </div>
 *
 *                             <div class="mt-3">
 *                                 <h6 class="h6">输入示例</h6>
 *                                 <div class="quote">
 *                                     <pre style="margin-bottom: 0;"><code>a1b2c3</code></pre>
 *                                 </div>
 *                             </div>
 *
 *                             <div class="mt-3">
 *                                 <h6 class="h6">输出示例</h6>
 *                                 <div class="quote">
 *                                     <pre style="margin-bottom: 0;"><code>anumberbnumbercnumber</code></pre>
 *                                 </div>
 *                             </div>
 *
 *                                                             <div class="mt-3">
 *                                     <h6 class="h6">提示信息</h6>
 *                                     <div class="quote">
 *                                         数据范围：<br>
 * 1 &lt;= s.length &lt; 10000。                                    </div>
 *                                 </div>
 *                                                     </div>
 *
 * @author Gin
 * @description
 * @date 2023/12/26 23:43
 */
public class ReplaceNumber {
    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        //可以不用sin 可以直接用 String charAt判断
        char[] sin = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sin.length; i++) {
            if (Character.isDigit(sin[i])) {
                sb.append("number");
            } else {
                sb.append(sin[i]);
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); // 接收用户输入的字符串
        sc.close();
        char[] ss = s.toCharArray();
        int count = 0;
        int length = ss.length;
        //找出数字数量
        for (int i = 0; i < length; i++) {
            if (ss[i] >= '0' && ss[i] <= '9') {
                count++;
            }
        }
        //新的数组长度为旧的 + 5 number 为6 比1多5
        int len = ss.length + count * 5;
        char[] result = new char[len];
        //双指针一个是旧串一个是新串 i 走新串 j走旧串
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (ss[j] >= '0' && ss[j] <= '9') {
                result[i++] = 'n';
                result[i++] = 'u';
                result[i++] = 'm';
                result[i++] = 'b';
                result[i++] = 'e';
                result[i] = 'r';
            } else {
                result[i] = ss[j];
            }
        }
        System.out.println(new String(result));
    }
}
