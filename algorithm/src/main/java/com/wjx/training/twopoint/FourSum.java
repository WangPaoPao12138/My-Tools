package com.wjx.training.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @date 2023/12/30 12:22
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
        for (int j = 0; j < nums.length - 3; j++) {
            //去重
            if (j > 0 && nums[j] == nums[j - 1]) continue;
            for (int i = j + 1; i < nums.length - 2; i++) {
                if (i > j + 1 && nums[i] == nums[i - 1]) continue;
                int left = i + 1;
                int right = nums.length - 1;
                //寻找另外两个
                while (left < right) {
                    long sum = (long) nums[j] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[j], nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
