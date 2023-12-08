package com.wjx.exam.hw.y2023.q4;

import java.util.*;

/**
 * 95%
 * <p>
 * <div data-v-6b1b450f="" data-v-344b70b2="" class="tw-text-gray-content tw-mt-4"><div data-v-6b1b450f="" class="tw-text-size-head rich-panel"><div>  某云短信厂商，为庆祝国庆，推出充值优惠活动。 </div> <div>  现在给出客户预算，和优惠<span>售价</span>序列，求最多可获得的短信总条数。<br> </div> <div> </div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="title tw-mt-6">输入描述</div> <div data-v-6b1b450f="" class="desc tw-mt-3 rich-panel"><div>第一行客户预算M，其中 0&lt;=M&lt;=1000000</div><div>第二行给出售价表，P1,P2...Pn, 其中 1&lt;=n&lt;=100，Pi为充值i元获得的短信条数。 1&lt;=Pi&lt;=1000， 1&lt;=n&lt;=100<br></div></div> <div data-v-6b1b450f="" class="title tw-mt-6">输出描述</div> <div data-v-6b1b450f="" class="desc tw-mt-3 rich-panel">最多获得的短信条数</div></div> <div data-v-6b1b450f="" class="tw-mt-6"><div data-v-6b1b450f="" class="title">示例1</div> <div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-sm-pure tw-mt-3">输入输出示例仅供调试，后台判题数据一般不包含示例</div> <div data-v-6b1b450f="" class="example"><div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输入</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class=""></span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">6
 * 10 20 30 40 60</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输出</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class=""></span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">70</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">说明</div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="" class="rich-panel">分两次充值最优，1元、5元各充一次。总条数 10+60=70</div></div></div></div></div><div data-v-6b1b450f="" class="tw-mt-6"><div data-v-6b1b450f="" class="title">示例2</div> <div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-sm-pure tw-mt-3">输入输出示例仅供调试，后台判题数据一般不包含示例</div> <div data-v-6b1b450f="" class="example"><div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输入</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class=""></span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">15
 * 10 20 30 40 60 60 70 80 90 150</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-flex tw-flex-row tw-justify-between"><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">输出</div> <button data-v-6b1b450f="" type="button" class="el-button el-button--text is-no-padding"><!----><!----><span class=""></span></button></div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="">210</div></div></div> <div data-v-6b1b450f=""><div data-v-6b1b450f="" class="tw-text-gray-700 tw-text-base-pure">说明</div> <div data-v-6b1b450f="" class="example-content"><div data-v-6b1b450f="" class="rich-panel">分两次充值最优，10元、5元各充一次。总条数 150+60=210</div></div></div></div></div> <!----></div>
 *
 * @author Gin
 * @description
 * @date 2023/4/15 15:48
 */
public class Topic03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int yuSuan = scanner.nextInt();
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int i = 1;
        while (scanner.hasNextInt()) {
            countMap.put(i, scanner.nextInt());
            i++;
        }
        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(countMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //性价比降序
                int result = new Float((new Float(o2.getValue()) / o2.getKey())).compareTo((new Float(o1.getValue()) / o1.getKey()));
                //价格升序
                if (result == 0) result = o1.getKey().compareTo(o2.getKey());
                return result;
            }
        });
        int result = 0;
        for (int j = 0; j < entries.size() && yuSuan > 0; j++) {
            Map.Entry<Integer, Integer> integerFloatEntry = entries.get(j);
            Integer count = integerFloatEntry.getKey();
            Integer countNum = integerFloatEntry.getValue();
            if (yuSuan >= count) {
                int num = yuSuan / count;
                yuSuan = yuSuan % count;
                result = result + num * countNum;
            }
        }
        System.out.println(result);

    }
}
