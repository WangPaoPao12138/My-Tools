package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/">34. 在排序数组中查找元素的第一个和最后一个位置</a></h1>
 * <p>
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。<br>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: nums = [5,7,7,8,8,10], target = 8<br>
 * 输出: [3,4]<br>
 * <h2>示例 2:</h2>
 * 输入:nums = [5,7,7,8,8,10], target = 6<br>
 * 输出: [-1,-1]<br>
 * <h2>示例 3:</h2>
 * 输入: nums = [], target = 0<br>
 * 输出: [-1,-1]<br>
 * <p>
 * 提示：<br>
 * <li>0 <= nums.length <= 105</li>
 * <li>-109 <= nums[i] <= 109</li>
 * <li>nums 是一个非递减数组</li>
 * <li>-109 <= target <= 109</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/7 21:34
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray array = new FindFirstAndLastPositionOfElementInSortedArray();
        System.out.println(array.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
    }

    public int[] searchRange(int[] nums, int target) {
        //[] 左闭右闭 找到第一个大于等于target的数
        int left = findLeftIdx(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = findLeftIdx(nums, target + 1) - 1;
        return new int[]{left, right};
    }

    //[5,7,7,8,8,10]
    private int findLeftIdx(int[] nums, int target) {
        int left = 0;//第一个大于等于target   left左边一定< target
        int right = nums.length - 1;//大于等于 target  right 的右边一定是 >= target
        while (left <= right) { //区间不为空
            int middle = left + (right - left) / 2; //中位数
            if (nums[middle] >= target) { //middle所在的值大于target 则可以继续缩小区间，right就缩小
                right = middle - 1; //middle-1 则 right 最多<=target  right 的右边一定是>= target
            } else {
                left = middle + 1; //如果是小于 所在值 则 middle+1 则 <= target的值 最多是第一个=的值 left左边一定< target
            }
        }
        return left;
    }

    //分别处理
    int[] searchRange01(int[] nums, int target) {
        //找出左边界
        int leftBorder = getLeftBorder(nums, target);
        //找出右边界
        int rightBorder = getRightBorder(nums, target);
        // 情况一 找不到左右边界/有一个边界找不到
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        // 情况三 区间存在
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二 区间不存在
        return new int[]{-1, -1};
    }

    //左右闭
    int getRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2; // 记录一下rightBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] > target) { //中间值大于目标
                right = middle - 1;  //右边左移 right的右边 必然>target
            } else { // 寻找右边界，nums[middle] == target的时候更新left
                left = middle + 1; //left的左边<= target
                rightBorder = left; //右边界
            }
        }
        return rightBorder;
    }

    //左右闭
    int getLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2; // 记录一下leftBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] >= target) { // 寻找左边界，nums[middle] == target的时候更新right
                right = middle - 1; //right 右边 大于等于 target   左边界
                leftBorder = right;
            } else {
                left = middle + 1; //left左边 小于 target
            }
        }
        return leftBorder;
    }
}
