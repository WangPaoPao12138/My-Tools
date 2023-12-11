package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">26. 删除有序数组中的重复项</a></h1>
 * 给你一个 非严格递增排列 的数组 nums ，<br>
 * 请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。<br>
 * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。<br>
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：<br>
 * <li>更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。</li>
 * <li>返回 k 。</li>
 * 判题标准:<br>
 * 系统会用下面的代码来测试你的题解:<br>
 * <pre>
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 * int k = removeDuplicates(nums); // 调用
 * assert k == expectedNums.length;
 * for (int i = 0; i &lt; k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }</pre>
 * 如果所有断言都通过，那么您的题解将被 <strong>通过</strong>。
 * <h2>示例 1:</h2>
 * 输入: nums = [1,1,2]<br>
 * 输出: 2, nums = [1,2,_]<br>
 * 解释: 函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。<br>
 * <h2>示例 2:</h2>
 * 输入: nums = [0,0,1,1,1,2,2,3,3,4]<br>
 * 输出: 5, nums = [0,1,2,3,4]<br>
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 3 * 10<sup>4</sup></li>
 * <li>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></li>
 * <li>nums 已按 非严格递增 排列</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/9 21:55
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray array = new RemoveDuplicatesFromSortedArray();
        array.removeDuplicates(new int[]{1,1,2});
    }
    //双指针
    public int removeDuplicates(int[] nums) {
        int pre = 0;
        int cur;
        for (cur = 0; cur < nums.length; cur++) {
            if (nums[pre] != nums[cur]) {
                pre++;
                nums[pre] = nums[cur];
            }
        }
        return pre+1;
    }
}
