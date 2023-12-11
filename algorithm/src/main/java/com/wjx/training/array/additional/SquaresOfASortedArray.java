package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/squares-of-a-sorted-array/">977. 有序数组的平方</a></h1>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：nums = [-4,-1,0,3,10]<br>
 * 输出：[0,1,9,16,100]<br>
 * 解释：平方后，数组变为 [16,1,0,9,100]<br>
 * <h2>示例 2:</h2>
 * 输入：nums = [-7,-3,2,3,11]<br>
 * 输出：[4,9,9,49,121]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 10<sup>4</sup></li>
 * <li>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></li>
 * <li>nums 已按 非递减顺序 排序</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/10 19:52
 */
public class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        //大的一定在两边
        int left = 0;
        int right = nums.length - 1;
        int curIdx = nums.length - 1;
        int[] result = new int[nums.length];
        while (left <= right) {
            int leftResult = nums[left] * nums[left];
            int rightResult = nums[right] * nums[right];
            if (rightResult>leftResult){
                result[curIdx] = rightResult;
                right--;
            }else {
                result[curIdx] = leftResult;
                left++;
            }
            curIdx--;
        }
        return result;
    }
}
