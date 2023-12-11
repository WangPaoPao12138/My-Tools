package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/move-zeroes/">283. 移动零</a></h1>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。<br>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: nums = [0,1,0,3,12]<br>
 * 输出: [1,3,12,0,0]<br>
 * <h2>示例 2:</h2>
 * 输入: nums = [0]<br>
 * 输出: [0]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 10<sup>4</sup></li>
 * <li>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/9 22:13
 */
public class MoveZeroes {
    public static void main(String[] args) {
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes01(new int[]{3,0,1});
    }

    //自写双指针
    public void moveZeroes01(int[] nums) {
        int left = 0;//0元素
        int right;//找到非0换位置
        for (right = 0; right < nums.length; right++) {
              // 这种默认 left < right 所以right交换后必为0
//            while (nums[left] != 0 && left < nums.length - 1) {
//                left++;
//            }
//            if (nums[right] != 0 && left < right) {
//                nums[left] = nums[right];
//                nums[right] = 0;
//            }
            //只遵循右边不为0则交换 则默认左边必然是需要被换的数,遵循循环不变量规则.
            if (nums[right] != 0 ) {
                int temp = nums[left];
                nums[left] = nums[right];
                //坑点在这里 temp还是得交换
                nums[right] = temp;
                left++;
            }
        }
    }

    //官方答案
    public void moveZeroes02(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
