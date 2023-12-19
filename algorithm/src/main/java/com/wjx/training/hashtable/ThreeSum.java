package com.wjx.training.hashtable;

import java.util.*;

/**
 * <h1><a href="https://leetcode.cn/problems/3sum/">15. 三数之和</a></h1>
 * <p>
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请<br>
 * 你返回所有和为 0 且不重复的三元组。<br>
 * 注意：答案中不可以包含重复的三元组。<br>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：nums = [-1,0,1,2,-1,-4]<br>
 * 输出：[[-1,-1,2],[-1,0,1]]<br>
 * 解释：<br>
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。<br>
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。<br>
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。<br>
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。<br>
 * <h2>示例 2:</h2>
 * 输入：nums = [0,1,1]<br>
 * 输出：[]<br>
 * <h2>示例 3:</h2>
 * 输入：nums = [0,0,0]<br>
 * 输出：[[0,0,0]]<br>
 * 解释：唯一可能的三元组和为 0 。<br>
 * <p>
 * 提示：<br>
 * <li>3 <= nums.length <= 3000</li>
 * <li>-10<sup>5</sup> <= nums[i] <= 10<sup>5</sup></li>
 *
 * @author Gin
 * @description
 * @date 2023/12/19 11:16
 */
public class ThreeSum {
    // hash 表
    public List<List<Integer>> threeSum01(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {//a去重
                continue;
            }
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 2 && nums[j] == nums[j - 1] //要给c留位置所以从+2开始找
                        && nums[j - 1] == nums[j - 2]) {//b去重
                    continue;
                }
                int c = 0 - (nums[i] + nums[j]);//找C
                if (set.contains(c)) {
                    ArrayList<Integer> objects = new ArrayList<>();
                    objects.add(nums[i]);
                    objects.add(nums[j]);
                    objects.add(c);
                    result.add(objects);
                    set.remove(c);// 三元组元素c去重
                } else {
                    //放c
                    set.add(nums[j]);
                }

            }
        }
        return result;
    }

    //双指针
    public List<List<Integer>> threeSum(int[] nums) {
        //排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length + 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                //左右移动位置
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重逻辑应该放在找到一个三元组之后，对b 和 c去重 找到最后一个相同的位置退出
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    //找到下一个不同的数
                    right--;
                    left++;
                }
            }
        }

        return result;
    }
}
