package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/search-insert-position/">35. 搜索插入位置</a></h1>
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。<br>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: nums = [1,3,5,6], target = 5<br>
 * 输出: 2<br>
 * <h2>示例 2:</h2>
 * 输入: nums = [1,3,5,6], target = 2<br>
 * 输出: 1<br>
 * <p>
 * <h2>示例 3:</h2>
 * 输入: nums = [1,3,5,6], target = 7<br>
 * 输出: 4<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 10^4</li>
 * <li>-10^4 <= nums[i] <= 10^4</li>
 * <li>nums 为 无重复元素 的 升序 排列数组</li>
 * <li>-10^4 <= target <= 10^4</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/5 21:39
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        searchInsertPosition.searchInsert02(new int[]{2,3,5,6},1);
        searchInsertPosition.searchInsert02(new int[]{2,3,5,6},7);
    }
    //暴力是O(N) 不符合题意
    public int searchInsert01(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>=target){
                return i;
            }
        }
        return nums.length;
    }
    // O(log n)必须用二分 符合题意 左闭右闭
    public int searchInsert02(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return right + 1;
    }
    // O(log n)必须用二分 符合题意 左闭右开
    public int searchInsert03(int[] nums, int target) {
        int left = 0;
        int right = nums.length ;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                return middle;
            }
        }
        return right;
    }
}
