package com.wjx.training.array;

/**
 * <h1><a href="https://leetcode.cn/problems/binary-search/">704. 二分查找</a></h1>
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: nums = [-1,0,3,5,9,12], target = 9<br>
 * 输出: 4<br>
 * 解释: 9 出现在 nums 中并且下标为 4<br>
 * <br>
 * <h2>示例 2:</h2>
 * 输入: nums = [-1,0,3,5,9,12], target = 2<br>
 * 输出: -1<br>
 * 解释: 2 不存在 nums 中因此返回 -1<br>
 * <br>
 * <p>
 * 提示：<br>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 * @author Gin
 * @description
 * @date 2023/11/30 21:01
 */
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        //示例1
        int[] example1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        //示例2
        int[] example2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
    }

    //左闭右闭区间
    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int num = nums[middle];
            //左移 middle 不是 则-1
            if (num > target) {
                right = middle - 1;
                //右移 middle 不是 则+1
            } else if (num < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    //左闭右开区间
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int num = nums[middle];
            //左移 middle 因为右开 所以不-1
            if (num > target) {
                right = middle;
                //右移 middle 不是 则+1
            } else if (num < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
