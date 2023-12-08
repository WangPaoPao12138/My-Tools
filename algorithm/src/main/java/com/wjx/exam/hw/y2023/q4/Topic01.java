package com.wjx.exam.hw.y2023.q4;

import java.util.*;

/**
 * 通过46.67%
 * <p>
 * <div data-v-6b1b450f="" data-v-344b70b2="" class="tw-text-gray-content tw-mt-4"><div data-v-6b1b450f="" class="tw-text-size-head rich-panel"><div>  给定一个字符串s，s包含以空格分隔的若干个单词，请对s进行如下处理后输出： </div> <div>  1、单词内部调整：对每个单词字母重新按字典序排序； </div> <div>  2、单词间顺序调整： </div> <div>  &nbsp; &nbsp; 1）统计每个单词出现的次数，并按次数降序排列； </div> <div>  &nbsp; &nbsp; 2）次数相同时，按单词长度升序排列； </div> <div>  &nbsp; &nbsp; 3）次数和单词长度均相同时，按字典序升序排列。 </div> <div>  请输出处理后的字符串，每个单词以一个空格分隔。 </div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="title tw-mt-6">输入描述</div> <div data-v-6b1b450f="" class="desc tw-mt-3 rich-panel">一行字符串，每个字符取值范围：[a-zA-Z0-9]以及空格，字符串长度范围：[1, 1000]</div> <div data-v-6b1b450f="" class="title tw-mt-6">输出描述</div> <div data-v-6b1b450f="" class="desc tw-mt-3 rich-panel">重新排序后的字符串，每个单词间隔1个空格，且首尾无空格</div></div> <div data-v-6b1b450f="" class="tw-mt-6"><div data-v-6b1b450f="" class="title">示例1</div> <div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-sm-pure tw-mt-3">输入输出示例仅供调试，后台判题数据一般不包含示例</div> <div data-v-6b1b450f="" class="example"><div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输入</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">复制</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">This is an apple</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输出</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">复制</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">an is This aelpp</div></div></div> <!----></div></div><div data-v-6b1b450f="" class="tw-mt-6"><div data-v-6b1b450f="" class="title">示例2</div> <div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-sm-pure tw-mt-3">输入输出示例仅供调试，后台判题数据一般不包含示例</div> <div data-v-6b1b450f="" class="example"><div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输入</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">复制</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">My sister is in the house not in the yard</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输出</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">复制</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">in in eht eht My is not adry ehosu eirsst</div></div></div> <!----></div></div> <!----></div>
 *
 * @author Gin
 * @description
 * @date 2023/4/15 14:28
 */
public class Topic01 {

    public static void main000(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputJuZi = scanner.nextLine().trim();
        String[] s = inputJuZi.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            map.put(s[i], map.getOrDefault(sortLetter(s[i]), 0) + 1);
        }
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //次数降序
                int result = o2.getValue() - o1.getValue();
                //长度升序
                if (result == 0) result = o1.getKey().length() - o2.getKey().length();
                //字典升序
                if (result == 0) result = o1.getKey().compareTo(o2.getKey());
                return result;
            }
        });
        for (int i = 0; i < entries.size(); i++) {
            Integer value = entries.get(i).getValue();
            String key = entries.get(i).getKey();
            for (int j = value; j > 0; j--) {
                System.out.print(key + " ");
            }
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputJuZi = scanner.nextLine().trim();
        String[] s = inputJuZi.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            map.put(s[i], map.getOrDefault(s[i], 0) + 1);
        }
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //次数降序
                int result = o2.getValue() - o1.getValue();
                //长度升序
                if (result == 0) result = o1.getKey().length() - o2.getKey().length();
                //字典升序
                if (result == 0) result = o1.getKey().compareTo(o2.getKey());
                return result;
            }
        });
        for (int i = 0; i < entries.size(); i++) {
            Integer value = entries.get(i).getValue();
            String key = sortLetter(entries.get(i).getKey());
            for (int j = value; j > 0; j--) {
                System.out.print(key + " ");
            }
        }
    }
    //字典排序
    private static String sortLetter(String letter) {
        char[] chars = letter.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    public static void main002(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputJuZi = scanner.nextLine().trim();

        String[] s = inputJuZi.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].trim();
            char[] chars = s[i].toCharArray();
            Arrays.sort(chars);
            s[i] = new String(chars);
            map.computeIfAbsent(s[i], s1 -> 0);
            map.put(s[i], map.get(s[i]) + 1);
        }
        Arrays.sort(s, (s1, s2) -> {
            int res = map.get(s2) - map.get(s1);
            return res == 0 ? (s1.length() == s2.length() ? s1.compareTo(s2) : s1.length() - s2.length()) : res;
        });
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            if (i != s.length - 1){
                res.append(s[i]).append(" ");
            }
            else {
                res.append(s[i]);
            }
        }
        System.out.println(res);
    }
}
