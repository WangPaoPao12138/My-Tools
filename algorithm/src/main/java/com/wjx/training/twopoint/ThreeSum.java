package com.wjx.training.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
 * @date 2023/12/30 12:08
 */
public class ThreeSum {

    //双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //i为起始位
        for (int i = 0; i < nums.length; i++) {
            //剪枝
            if (nums[i] > 0) break;
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            //寻找另外两个
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left<right && nums[left]==nums[left+1])left++;
                    while (left<right && nums[right]==nums[right-1])right--;
                    left++;
                    right--;
                }

            }
        }
        return result;
    }
}
