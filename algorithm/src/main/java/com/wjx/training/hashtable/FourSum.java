package com.wjx.training.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <h1><a href="https://leetcode.cn/problems/4sum/">18. 四数之和</a></h1>
 * <p>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。<br>
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]<br>
 * （若两个四元组元素一一对应，则认为两个四元组重复）：<br>
 * <li>0 <= a, b, c, d < n</li>
 * <li>a、b、c 和 d 互不相同</li>
 * <li>nums[a] + nums[b] + nums[c] + nums[d] == target</li>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0<br>
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]<br>
 * <h2>示例 2:</h2>
 * 输入：nums = [2,2,2,2,2], target = 8<br>
 * 输出：[[2,2,2,2]]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 200</li>
 * <li>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></li>
 * <li>-10<sup>9</sup> <= target <= 10<sup>9</sup></li>
 *
 * @author Gin
 * @description
 * @date 2023/12/18 23:01
 */
public class FourSum {
    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        System.out.println(fourSum.fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296));
    }
    //双指针
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;
        Arrays.sort(nums);
        //1.先两层for循环 找 a b两个位置
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {//nums[i]去重
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {//nums[j]去重
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                //2.再双指针逼近目标节点位置
                while (left < right) {
                    //防止溢出
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    //大了右边左移
                    if (sum > target) {
                        right--;
                    //小了左边右移
                    } else if (sum < target) {
                        left++;
                    } else {
                        //符合情况
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 对nums[left]和nums[right]去重
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
