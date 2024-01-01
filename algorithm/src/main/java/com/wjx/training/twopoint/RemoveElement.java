package com.wjx.training.twopoint;

/**
 * <h1><a href="https://leetcode.cn/problems/remove-element/">27. 移除元素</a></h1>
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <br>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <br>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <br>
 * <p>
 * 说明:<br>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <br>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <br>
 * 你可以想象内部操作如下:<br>
 * //nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝 <br>
 * int len=removeElement(nums,val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。<br>
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * <br>
 * for(int i=0;i &lt; len; i++){
 * <br>
 * &emsp; print(nums[i]);
 * <br>
 * }
 *
 * @author Gin
 * @description
 * @date 2023/12/26 15:10
 */
public class RemoveElement {
    //双指针一起向右 遍历两遍
    public int removeElement01(int[] nums, int val) {
        int fast;
        int slow = 0;
        for (fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val)
                nums[slow++] = nums[fast];
        }
        return slow;
    }

    //双指针 优化 遍历一遍 左右向心
    public int removeElement02(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right--];
            } else {
                left++;
            }
        }
        return left;
    }
}
