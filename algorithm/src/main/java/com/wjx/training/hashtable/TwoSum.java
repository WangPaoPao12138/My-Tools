package com.wjx.training.hashtable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <h1><a href="https://leetcode.cn/problems/two-sum/">1. 两数之和</a></h1>
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。<br>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。<br>
 * 你可以按任意顺序返回答案。<br>
 * <h2>示例 1:</h2>
 * 输入：nums = [2,7,11,15], target = 9<br>
 * 输出：[0,1]<br>
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。<br>
 * <h2>示例 2:</h2>
 * 输入：nums = [3,2,4], target = 6<br>
 * 输出：[1,2]<br>
 * <h2>示例 3:</h2>
 * 输入：nums = [3,3], target = 6<br>
 * 输出：[0,1]<br>
 * <p>
 * 提示：<br>
 * <li>2 <= nums.length <= 10<sup>4</sup> </li>
 * <li>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup> </li>
 * <li>-10<sup>9</sup>  <= target <= 10<sup>9</sup> </li>
 * <li>只会存在一个有效答案</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/18 22:49
 */
public class TwoSum {
    //暴力
    public int[] twoSum01(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    //哈希表
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
