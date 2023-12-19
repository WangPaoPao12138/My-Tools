package com.wjx.training.hashtable;

import java.util.HashMap;

/**
 * <h1><a href="https://leetcode.cn/problems/4sum-ii/">454. 四数相加 II</a></h1>
 * <p>
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * <li>0 <= i, j, k, l < n</li>
 * <li>nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0</li>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]<br>
 * 输出：2<br>
 * 解释：<br>
 * 两个元组如下：<br>
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0<br>
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0<br>
 * <h2>示例 2:</h2>
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]<br>
 * 输出：1<br>
 * <p>
 * 提示：<br>
 * <li>n == nums1.length</li>
 * <li>n == nums2.length</li>
 * <li>n == nums3.length</li>
 * <li>n == nums4.length</li>
 * <li>1 <= n <= 200</li>
 * <li>-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/18 23:01
 */
public class FourSumii {
    //分组抽象为两数之和
    //哈希表
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum1 = nums1[i] + nums2[j];
                map.put(sum1, map.getOrDefault(sum1, 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum2 = nums3[i] + nums4[j];
                result += map.getOrDefault(-sum2, 0);
            }
        }
        return result;
    }
}
