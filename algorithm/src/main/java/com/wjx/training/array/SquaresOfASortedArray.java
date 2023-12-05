package com.wjx.training.array;

import java.util.Arrays;

/**
 * <h1><a href="https://leetcode.cn/problems/squares-of-a-sorted-array/">977. 有序数组的平方</a></h1>
 * <p>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：nums = [-4,-1,0,3,10]<br>
 * 输出：[0,1,9,16,100]<br>
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]<br>
 * <br>
 * <h2>示例 2:</h2>
 * 输入：nums = [-7,-3,2,3,11]<br>
 * 输出：[4,9,9,49,121]<br>
 * 解释: 2 不存在 nums 中因此返回 -1<br>
 * <br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 104</li>
 * <li>-104 <= nums[i] <= 104</li>
 * <li>nums 已按 非递减顺序 排序</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/3 9:19
 */
public class SquaresOfASortedArray {
    // 平方后排序
    public int[] sortedSquares01(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    //双指针排序
    public int[] sortedSquares02(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int resultIdx = nums.length - 1;
        while (left <= right) {
            int left2 = nums[left] * nums[left];
            int right2 = nums[right]* nums[right];
            if (left2 > right2) {
                result[resultIdx] = left2;
                left++;
            } else {
                result[resultIdx] = right2;
                right--;
            }
            resultIdx--;
        }
        return result;
    }
}
