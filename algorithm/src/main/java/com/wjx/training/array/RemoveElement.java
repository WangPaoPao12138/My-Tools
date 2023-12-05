package com.wjx.training.array;

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
 * @date 2023/12/1 20:39
 */
public class RemoveElement {
    public static void main(String[] args) {

    }

    //暴力
    public int removeElement01(int[] nums, int val) {
        int result = nums.length;
        for (int i = 0; i < result; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < result; j++) {
                    nums[j - 1] = nums[j];
                }
                i--; //数据前移则i也前移
                result--;
            }
        }
        return result;
    }

    //双指针 不符合则表明快指针位置为所需要位置数据
    public int removeElement02(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    //双向指针 一
    public int removeElement03(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置
        while (left <= right) {
            if (nums[left] == val) { //left位置的元素需要移除
                //将right位置的元素移到left（覆盖），right位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            //若连续相等则连续左移
            while (right >= 0 && nums[right] == val) right--;
        }
        return left;
    }

    //双向指针 二
    public int removeElement04(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            //左指针值 和 目标值 相等 则只移动 右指针 并且不断移动到 左指针值不等则左指针右移
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                // 这里兼容了right指针指向的值与val相等的情况
                left++;
            }
        }
        return left;
    }
}
