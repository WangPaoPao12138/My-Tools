package com.wjx.training.string;

import java.util.Scanner;

/**
 * <h1><a href="https://kamacoder.com/problempage.php?pid=1065">55. 右旋字符串（第八期模拟笔试）</a></h1>
 * <div class="p-4 overflow-auto">
 * <div class="small-font text-body-secondary">
 * 时间限制：1.000S&nbsp;&nbsp;空间限制：128MB
 * </div>
 * <div class="mt-3">
 * <h6 class="h6">题目描述</h6>
 * <div class="quote">
 * <p>
 * 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。给定一个字符串 s 和一个正整数 k，请编写一个函数，将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。&nbsp;
 * </p>
 * <p>
 * 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"。
 * </p>                                </div>
 * </div>
 * <div class="mt-3">
 * <h6 class="h6">输入描述</h6>
 * <div class="quote">
 * 输入共包含两行，第一行为一个正整数 k，代表右旋转的位数。第二行为字符串 s，代表需要旋转的字符串。                                </div>
 * </div>
 * <div class="mt-3">
 * <h6 class="h6">输出描述</h6>
 * <div class="quote">
 * 输出共一行，为进行了右旋转操作后的字符串。                                </div>
 * </div>
 * <div class="mt-3">
 * <h6 class="h6">输入示例</h6>
 * <div class="quote">
 * <pre style="margin-bottom: 0;"><code>2
 * abcdefg</code></pre>
 * </div>
 * </div>
 * <div class="mt-3">
 * <h6 class="h6">输出示例</h6>
 * <div class="quote">
 * <pre style="margin-bottom: 0;"><code>fgabcde</code></pre>
 * </div>
 * </div>
 * <div class="mt-3">
 * <h6 class="h6">提示信息</h6>
 * <div class="quote">
 * 数据范围：<br>
 * 1 &lt;= k &lt; 10000,<br>
 * 1 &lt;= s.length &lt; 10000;                                    </div>
 * </div>
 * </div>
 *
 * @author Gin
 * @description
 * @date 2023/12/21 17:31
 */
public class DextrorotatoryString {
    //StringBuffer api
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int count = Integer.valueOf(scanner.nextLine().trim());;
//        String str = scanner.nextLine().trim();
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(str.substring(str.length() - count));
//        stringBuffer.append(str.substring(0, str.length() - count));
//        System.out.println(stringBuffer);
//    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int k = sc.nextInt();
//        sc.nextLine();
//        String s = sc.nextLine();
//        System.out.print(s.substring(k));;//需要右边旋转的字符串
//        System.out.print(s.substring(0,k));
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.valueOf(scanner.nextLine().trim());;
        String strInput = scanner.nextLine().trim();
        char[] str = strInput.toCharArray();
        reverseString(str,0,str.length-1);
        reverseString(str,0,count-1);
        reverseString(str,count,str.length-1);
        System.out.println(new String(str));

    }
    public static void reverseString(char[] str, int left, int right){
        while (left<right){
            char c = str[right];
            str[right] = str[left];
            str[left]=c;
            left++;
            right--;
        }
    }
}
