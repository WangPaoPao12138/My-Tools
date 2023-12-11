package com.wjx.training.array.additional;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <h1><a href="https://leetcode.cn/problems/fruit-into-baskets/">904. 水果成篮</a></h1>
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。<br>
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：<br>
 * <li>你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。</li>
 * <li>你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。</li>
 * <li>一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。</li><br>
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：fruits = [1,2,1]<br>
 * 输出：3<br>
 * 解释：可以采摘全部 3 棵树。<br>
 * <h2>示例 2:</h2>
 * 输入：fruits = [0,1,2,2]<br>
 * 输出：3<br>
 * 解释：可以采摘 [1,2,2] 这三棵树。<br>
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。<br>
 * <h2>示例 3:</h2>
 * 输入：fruits = [1,2,3,2,2]<br>
 * 输出：4<br>
 * 解释：可以采摘 [2,3,2,2] 这四棵树。<br>
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。<br>
 * <h2>示例 4:</h2>
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]<br>
 * 输出：5<br>
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。<br>
 * <p>
 * 提示：<br>
 * <li>1 <= fruits.length <= 10<sup>5</sup></li>
 * <li>0 <= fruits[i] < fruits.length</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/10 20:03
 */
public class FruitIntoBaskets {
    //滑动窗口 用map
    public int totalFruit01(int[] fruits) {
        int result = 0;
        int left = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int right = 0; right < fruits.length; right++) {
            count.put(fruits[right], count.getOrDefault(fruits[right], 0) + 1);
            while (count.size() > 2) {
                count.put(fruits[left], count.get(fruits[left]) - 1);
                if (count.get(fruits[left]) == 0) count.remove(fruits[left]);
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    //滑动窗口 用set
    public int totalFruit02(int[] fruits) {
        int result = 0;
        HashSet<Integer> count = new HashSet<>();
        int left = 0;//滑动窗口起始位置
        int right;//滑动窗口结束位置
        int preIdx = 0;//前一个位置和当前位置数字不同
        for (right = 0; right < fruits.length; right++) {
            count.add(fruits[right]);
            if (count.size()>2){
                count.clear();
                left = preIdx;
                count.add(fruits[right]);
                count.add(fruits[left]);
            }
            result = Math.max(result,right-left+1);
            if (fruits[preIdx] != fruits[right]) {
                preIdx = right;
            }
        }
        return result;
    }
}
