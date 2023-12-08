package com.wjx.exam.hw.y2023.q4;

import java.util.*;

/**
 * 65% 超时 排序 有问题= =
 *
 * <p>
 * <div data-v-6b1b450f="" data-v-344b70b2="" class="tw-text-gray-content tw-mt-4"><div data-v-6b1b450f="" class="tw-text-size-head rich-panel">小明有 N 块木板，第 i (1<=i<=n)块木板的长度为 a i。<br> <br> 小明买了一块长度为<img alt="m" src="https://www.nowcoder.com/equation?tex=m">的木料，这块木料可以切割成任意块，拼接到已有的木板上，用来加长木板。<br> <br> 小明想让最短的木板尽量长。<br> <br> 请问小明加长木板后，最短木板的长度最大可以为多少？</div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="title tw-mt-6">输入描述</div> <div data-v-6b1b450f="" class="desc tw-mt-3 rich-panel">输入的第一行包含两个正整数，<img src="https://www.nowcoder.com/equation?tex=n(1%20%5Cleq%20n%20%5Cleq%2010%5E3)">，<img src="https://www.nowcoder.com/equation?tex=m(1%20%5Cleq%20m%20%5Cleq%2010%5E6)">——<img alt="n" src="https://www.nowcoder.com/equation?tex=n">表示木板数，<img alt="m" src="https://www.nowcoder.com/equation?tex=m">表示木料长度。<br><br>输入的第二行包含<img alt="n" src="https://www.nowcoder.com/equation?tex=n">个正整数，<img src="https://www.nowcoder.com/equation?tex=a_1%2Ca_2%2C%5Cldots%2Ca_n"> (<img src="https://www.nowcoder.com/equation?tex=1%5Cle%20a_i%5Cle%2010%5E6">)。</div> <div data-v-6b1b450f="" class="title tw-mt-6">输出描述</div> <div data-v-6b1b450f="" class="desc tw-mt-3 rich-panel">输出的唯一一行包含一个正整数，表示加长木板后，最短木板的长度最大可以为多少？</div></div> <div data-v-6b1b450f="" class="tw-mt-6"><div data-v-6b1b450f="" class="title">示例1</div> <div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-sm-pure tw-mt-3">输入输出示例仅供调试，后台判题数据一般不包含示例</div> <div data-v-6b1b450f="" class="example"><div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输入</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">：</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">5 3
 * 4 5 3 5 5</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输出</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">：</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">5</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">说明</div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="" class="rich-panel">给第<img src="https://www.nowcoder.com/equation?tex=1" alt="1">块木板长度增加<img src="https://www.nowcoder.com/equation?tex=1" alt="1">，给第<img src="https://www.nowcoder.com/equation?tex=3" alt="3">块木板长度增加<img src="https://www.nowcoder.com/equation?tex=2" alt="2">后，这<img src="https://www.nowcoder.com/equation?tex=5" alt="5">块木板长度变为<img src="https://www.nowcoder.com/equation?tex=%5B5%2C5%2C5%2C5%2C5%5D" alt="[5,5,5,5,5]">，最短的木板的长度最大为<img src="https://www.nowcoder.com/equation?tex=5" alt="5">。</div></div></div></div></div><div data-v-6b1b450f="" class="tw-mt-6"><div data-v-6b1b450f="" class="title">示例2</div> <div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-sm-pure tw-mt-3">输入输出示例仅供调试，后台判题数据一般不包含示例</div> <div data-v-6b1b450f="" class="example"><div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输入</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">：</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">5 2
 * 4 5 3 5 5</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输出</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class="">：</span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">4</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">说明</div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="" class="rich-panel">给第<img src="https://www.nowcoder.com/equation?tex=3" alt="3">块木板长度增加<img src="https://www.nowcoder.com/equation?tex=1" alt="1">后，这<img src="https://www.nowcoder.com/equation?tex=5" alt="5">块木板长度变为<img src="https://www.nowcoder.com/equation?tex=%5B4%2C5%2C4%2C5%2C5%5D" alt="[4,5,4,5,5]">，剩余木料的长度为<img src="https://www.nowcoder.com/equation?tex=1" alt="1">。<br><br>此时剩余木料无论给哪块木板加长，最短木料的长度都为<img src="https://www.nowcoder.com/equation?tex=4" alt="4">。</div></div></div></div></div> <!----></div>
 *
 * @author Gin
 * @description
 * @date 2023/4/15 15:12
 */
public class Topic02 {
    //15%
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int mMuBan = scanner.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < count; i++) {
            int i1 = scanner.nextInt();
            map.put(i1, map.getOrDefault(i1, 0) + 1);
        }
        //拿到List操作
        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        //如果只
        while (entries.size() >= 2 && mMuBan > 0) {
            //最短加到次短需要多少木板
            Integer key = entries.get(0).getKey();
            Integer value = entries.get(0).getValue();
            Integer key1 = entries.get(1).getKey();
            Integer value1 = entries.get(1).getValue();
            Integer neededMuBan = (key1 - key) * value;
            //最所有短板补成次小板子
            if (neededMuBan <= mMuBan) {
                entries.get(1).setValue(value1 + value);
                entries.remove(0);
                mMuBan = mMuBan-neededMuBan;
            } else if (neededMuBan > mMuBan) {
                break;
            }
        }
        //TODO 输出是否要判断
        System.out.println(entries.get(0).getKey());
    }

    //65%
    public static void main11(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int mMuBan = scanner.nextInt();
        int[] mbArr = new int[count];
        for (int i = 0; i < count; i++) {
            mbArr[i] = scanner.nextInt();
        }
        Arrays.sort(mbArr);
        while (mMuBan > 0) {
            mbArr[0]++;
            mMuBan--;
            //耗时长 能否用map？
            Arrays.sort(mbArr);
        }
        System.out.println(mbArr[0]);
    }

}
