package com.wjx.training.hashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <h1><a href="https://leetcode.cn/problems/intersection-of-two-arrays/">349. 两个数组的交集</a></h1>
 * <p>
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]<br>
 * 输出：[2]<br>
 * <h2>示例 2:</h2>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]<br>
 * 输出：[9,4]<br>
 * 解释：[4,9] 也是可通过的<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums1.length, nums2.length <= 1000</li>
 * <li>0 <= nums1[i], nums2[i] <= 1000</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/17 19:08
 */
public class IntersectionOfOwoArrays {
    //HashSet实现
    public int[] intersection01(int[] nums1, int[] nums2) {
        HashSet<Integer> nums1Hash = new HashSet<>();
        HashSet<Integer> resultHash = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            nums1Hash.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums1Hash.contains(nums2[i])) resultHash.add(nums2[i]);
        }
        //方法1：将结果集合转为数组
//        return resultHash.stream().mapToInt(x -> x).toArray();
        //方法2：另外申请一个数组存放setRes中的元素,最后返回数组
        int[] result = new int[resultHash.size()];
        int idx = 0;
        for (Integer i : resultHash) {
            result[idx++] = i;
        }
        return result;
    }
    //数组
    public int[] intersection02(int[] nums1, int[] nums2) {
        int[] hash1 = new int[1002];
        int[] hash2 = new int[1002];
        for(int i : nums1)
            hash1[i]++;
        for(int i : nums2)
            hash2[i]++;
        List<Integer> resList = new ArrayList<>();
        for(int i = 0; i < 1002; i++)
            if(hash1[i] > 0 && hash2[i] > 0)
                resList.add(i);
        int index = 0;
        int res[] = new int[resList.size()];
        for(int i : resList)
            res[index++] = i;
        return res;
    }

    //数组
    public int[] intersection03(int[] nums1, int[] nums2) {
        int[] ints = new int[1000];
        for (int i = 0; i < nums1.length; i++) {
            ints[nums1[i]]++;
        }
        //Set 结果去重
        HashSet<Integer> resultHash = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (ints[nums2[i]]>0){
                resultHash.add(nums2[i]);
            }
        }
        int[] result = new int[resultHash.size()];
        int idx = 0;
        for (Integer i : resultHash) {
            result[idx++] = i;
        }
        return result;
    }
}
