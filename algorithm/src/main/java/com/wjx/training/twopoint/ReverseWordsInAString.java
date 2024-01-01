package com.wjx.training.twopoint;

/**
 * <h1><a href="https://leetcode.cn/problems/reverse-words-in-a-string/">151. 反转字符串中的单词</a></h1>
 * <p>
 * <p>
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。<br>
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。<br>
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。<br>
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。<br>
 * <h2>示例 1:</h2>
 * 输入：s = "the sky is blue"<br>
 * 输出："blue is sky the"<br>
 * <h2>示例 2:</h2>
 * 输入：s = "  hello world  "<br>
 * 输出："world hello"<br>
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。<br>
 * <h2>示例 3:</h2>
 * 输入：s = "a good   example"<br>
 * 输出："example good a"<br>
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个<br>
 * <p>
 * 提示：<br>
 * <li>1 <= s.length <= 10<sup>4</sup></li>
 * <li>s 仅由小写英文组成</li>
 * <li>1 <= k <= 10<sup>4</sup></li>
 *
 * @author Gin
 * @description
 * @date 2023/12/27 11:03
 */
public class ReverseWordsInAString {


    //所以解题思路如下：
//
//移除多余空格
//将整个字符串反转
//将每个单词反转
//举个例子，源字符串为："the sky is blue "
//
//移除多余空格 : "the sky is blue"
//字符串反转："eulb si yks eht"
//单词反转："blue is sky the"
    public static void main(String[] args) {
        ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();
        System.out.println(reverseWordsInAString.reverseWords("a good   example"));
    }

    //自己写的笨比方法
    //分割 双指针交换 append
//    public String reverseWords(String s) {
//        //存储单词List
//        ArrayList<String> tempList = new ArrayList<>();
//        for (int i = 0; i < s.length(); i++) {
//            String temp = "";
//            while (i < s.length() && s.charAt(i) != ' ') {
//                temp += s.charAt(i++);
//            }
//            if (temp != "") tempList.add(temp);
//        }
//        //双指针 单词反转
//        String str = "";
//        int left = 0;
//        int right = tempList.size() - 1;
//        while (left < right) {
//            String s1 = tempList.get(left);
//            tempList.add(left, tempList.get(right));
//            tempList.remove(++left);
//            tempList.add(right, s1);
//            tempList.remove(right + 1);
//            right--;
//        }
//        //存储
//        return tempList.stream().collect(Collectors.joining(" "));
//    }
    public String reverseWords(String s) {
        //移除多余空格
        StringBuffer sb = new StringBuffer();
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        while (start <= end) {
            if (s.charAt(start) != ' ' || sb.charAt(sb.length() - 1) != ' ')
                sb.append(s.charAt(start));
            start++;
        }
        //将整个字符串反转
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
        //将每个单词反转
        for (int i = 0; i < sb.length(); i++) {
            int wordStart = i;
            //i防止越界
            while (i < sb.length() && sb.charAt(i) != ' ') i++;
            int wordEnd = i - 1;
            while (wordStart < wordEnd) {
                char temp = sb.charAt(wordStart);
                sb.setCharAt(wordStart, sb.charAt(wordEnd));
                sb.setCharAt(wordEnd, temp);
                wordStart++;
                wordEnd--;
            }
        }
        //存储
        return sb.toString();
    }

    class Solution02 {
        /**
         * 不使用Java内置方法实现
         * <p>
         * 1.去除首尾以及中间多余空格
         * 2.反转整个字符串
         * 3.反转各个单词
         */
        public String reverseWords(String s) {
            // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
            // 1.去除首尾以及中间多余空格
            StringBuilder sb = removeSpace(s);
            // 2.反转整个字符串
            reverseString(sb, 0, sb.length() - 1);
            // 3.反转各个单词
            reverseEachWord(sb);
            return sb.toString();
        }

        private StringBuilder removeSpace(String s) {
            StringBuilder sb = new StringBuilder();
            int left = 0;
            int right = s.length() - 1;
            while (s.charAt(left) == ' ') left++;
            while (s.charAt(right) == ' ') right--;
            while (left <= right) {
                //!(s.charAt(left) == ' ' && sb.charAt(sb.length() - 1) == ' ')
                if (s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(s.charAt(left));
                }
            }
            return sb;
        }

        /**
         * 反转字符串指定区间[start, end]的字符
         */
        public void reverseString(StringBuilder sb, int start, int end) {
            while (start <= end) {
                char c = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, c);
            }
        }

        private void reverseEachWord(StringBuilder sb) {
            int start = 0;
            int end = 1;

            while (start < sb.length()) {
                while (end < sb.length() && sb.charAt(end) != ' ') {
                    end++;
                }
                reverseString(sb, start, end);
                start = end + 1;
                end = start + 1;
            }

        }
    }

    //解法二：创建新字符数组填充。时间复杂度O(n)
    class Solution03 {
        public String reverseWords(String s) {
            //源字符数组
            char[] initialArr = s.toCharArray();
            //新字符数组
            char[] newArr = new char[initialArr.length + 1];//下面循环添加"单词 "，最终末尾的空格不会返回
            int newArrPos = 0;
            //i来进行整体对源字符数组从后往前遍历
            int i = initialArr.length - 1;
            while (i >= 0) {
                while (i >= 0 && initialArr[i] == ' ') {
                    i--;
                }  //跳过空格
                //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
                int right = i;
                while (i >= 0 && initialArr[i] != ' ') {
                    i--;
                }
                //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
                for (int j = i + 1; j <= right; j++) {
                    newArr[newArrPos++] = initialArr[j];
                    if (j == right) {
                        newArr[newArrPos++] = ' ';//空格
                    }
                }
            }
            //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
            if (newArrPos == 0) {
                return "";
            } else {
                return new String(newArr, 0, newArrPos - 1);
            }
        }
    }

    //解法三：双反转+移位，String 的 toCharArray() 方法底层会 new 一个和原字符串相同大小的 char 数组，空间复杂度：O(n)
    class Solution04 {
        /**
         * 思路：
         * ①反转字符串  "the sky is blue " => " eulb si yks eht"
         * ②遍历 " eulb si yks eht"，每次先对某个单词进行反转再移位
         * 这里以第一个单词进行为演示：" eulb si yks eht" ==反转=> " blue si yks eht" ==移位=> "blue si yks eht"
         */
        public String reverseWords(String s) {
            //步骤1：字符串整体反转（此时其中的单词也都反转了）
            char[] initialArr = s.toCharArray();
            reverse(initialArr, 0, s.length() - 1);
            int k = 0;
            for (int i = 0; i < initialArr.length; i++) {
                if (initialArr[i] == ' ') {
                    continue;
                }
                int tempCur = i;
                while (i < initialArr.length && initialArr[i] != ' ') {
                    i++;
                }
                for (int j = tempCur; j < i; j++) {
                    if (j == tempCur) { //步骤二：二次反转
                        reverse(initialArr, tempCur, i - 1);//对指定范围字符串进行反转，不反转从后往前遍历一个个填充有问题
                    }
                    //步骤三：移动操作
                    initialArr[k++] = initialArr[j];
                    if (j == i - 1) { //遍历结束
                        //避免越界情况，例如=> "asdasd df f"，不加判断最后就会数组越界
                        if (k < initialArr.length) {
                            initialArr[k++] = ' ';
                        }
                    }
                }
            }
            if (k == 0) {
                return "";
            } else {
                //参数三：以防出现如"asdasd df f"=>"f df asdasd"正好凑满不需要省略空格情况
                return new String(initialArr, 0, (k == initialArr.length) && (initialArr[k - 1] != ' ') ? k : k - 1);
            }
        }

        public void reverse(char[] chars, int begin, int end) {
            for (int i = begin, j = end; i < j; i++, j--) {
                chars[i] ^= chars[j];
                chars[j] ^= chars[i];
                chars[i] ^= chars[j];
            }
        }
    }

    /*
     * 解法四：时间复杂度 O(n)
     * 参考卡哥 c++ 代码的三步骤：先移除多余空格，再将整个字符串反转，最后把单词逐个反转
     * 有别于解法一 ：没有用 StringBuilder  实现，而是对 String 的 char[] 数组操作来实现以上三个步骤
     */
    class Solution {
        //用 char[] 来实现 String 的 removeExtraSpaces，reverse 操作
        public String reverseWords(String s) {
            char[] chars = s.toCharArray();
            //1.去除首尾以及中间多余空格
            chars = removeExtraSpaces(chars);
            //2.整个字符串反转
            reverse(chars, 0, chars.length - 1);
            //3.单词反转
            reverseEachWord(chars);
            return new String(chars);
        }

        //1.用 快慢指针 去除首尾以及中间多余空格，可参考数组元素移除的题解
        public char[] removeExtraSpaces(char[] chars) {
            int slow = 0;
            for (int fast = 0; fast < chars.length; fast++) {
                //先用 fast 移除所有空格
                if (chars[fast] != ' ') {
                    //在用 slow 加空格。 除第一个单词外，单词末尾要加空格
                    if (slow != 0)
                        chars[slow++] = ' ';
                    //fast 遇到空格或遍历到字符串末尾，就证明遍历完一个单词了
                    while (fast < chars.length && chars[fast] != ' ')
                        chars[slow++] = chars[fast++];
                }
            }
            //相当于 c++ 里的 resize()
            char[] newChars = new char[slow];
            System.arraycopy(chars, 0, newChars, 0, slow);
            return newChars;
        }

        //双指针实现指定范围内字符串反转，可参考字符串反转题解
        public void reverse(char[] chars, int left, int right) {
            if (right >= chars.length) {
                System.out.println("set a wrong right");
                return;
            }
            while (left < right) {
                chars[left] ^= chars[right];
                chars[right] ^= chars[left];
                chars[left] ^= chars[right];
                left++;
                right--;
            }
        }

        //3.单词反转
        public void reverseEachWord(char[] chars) {
            int start = 0;
            //end <= s.length() 这里的 = ，是为了让 end 永远指向单词末尾后一个位置，这样 reverse 的实参更好设置
            for (int end = 0; end <= chars.length; end++) {
                // end 每次到单词末尾后的空格或串尾,开始反转单词
                if (end == chars.length || chars[end] == ' ') {
                    reverse(chars, start, end - 1);
                    start = end + 1;
                }
            }
        }
    }
}
