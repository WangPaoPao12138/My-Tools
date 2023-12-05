package com.wjx.training.array;

/**
 * <h1><a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">209. 长度最小的子数组</a></h1>
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <br>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <h2>示例 1:</h2>
 * 输入：target = 7, nums = [2,3,1,2,4,3]<br>
 * 输出：2<br>
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。<br>
 * <br>
 * <h2>示例 2:</h2>
 * 输入：target = 4, nums = [1,4,4]<br>
 * 输出：1<br>
 * <br>
 * <h2>示例 3：</h2>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]<br>
 * 输出：0<br>
 * <p>
 * 提示：<br>
 * <li>1 <= target <= 109</li>
 * <li>1 <= nums.length <= 105</li>
 * <li>1 <= nums[i] <= 105</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/3 9:35
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
        minimumSizeSubarraySum.minSubArrayLen01(15, new int[]{5, 1, 3, 5, 10, 7, 4, 9, 2, 8});
    }

    //滑动窗口
    public int minSubArrayLen01(int target, int[] nums) {
        int left = 0;
        int curSum = 0;
        int right;
        int mixResult = 0;
        for (right = 0; right < nums.length; right++) {
            curSum = curSum + nums[right];
            while (curSum >= target) {
                //踩坑点 找最小数 为0 的时候覆盖就行
                mixResult = mixResult == 0 ? right - left + 1 : Math.min(mixResult, right - left + 1);
                curSum = curSum - nums[left];
                left++;
            }
        }
        return mixResult;
    }

    //滑动窗口
    @Deprecated
    public int minSubArrayLen02(int target, int[] nums) {
        int left = 0;
        int curSum = 0;
        int right;
        int mixResult = Integer.MAX_VALUE;
        for (right = 0; right < nums.length; right++) {
            curSum = curSum + nums[right];
            while (curSum >= target) {
                //第二种处理方式 默认最大找最小即可 但是不符合题意
                mixResult = Math.min(mixResult, right - left + 1);
                curSum = curSum - nums[left];
                left++;
            }
        }
        return mixResult;
    }
}
