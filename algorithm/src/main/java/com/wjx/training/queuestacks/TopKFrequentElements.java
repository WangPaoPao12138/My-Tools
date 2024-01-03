package com.wjx.training.queuestacks;

import java.util.*;

/**
 * <h1><a href="https://leetcode.cn/problems/top-k-frequent-elements/">347. 前 K 个高频元素</a></h1>
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: nums = [1,1,1,2,2,3], k = 2<br>
 * 输出: [1,2]<br>
 * <h2>示例 2:</h2>
 * 输入: nums = [1], k = 1<br>
 * 输出: [1]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 10<sup>5</sup></li>
 * <li>k 的取值范围是 [1, 数组中不相同的元素的个数]</li>
 * <li>题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的</li>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * @author Gin
 * @description
 * @date 2024/1/3 22:10
 */
public class TopKFrequentElements {
    public int[] topKFrequent01(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //从大到小排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//大顶堆需要对所有元素进行排序
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }

    public int[] topKFrequent02(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //从小到大排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//大顶堆需要对所有元素进行排序
            if(pq.size()<k){//小顶堆元素个数小于k个时直接加
                pq.add(new int[]{entry.getKey(),entry.getValue()});
            }else{
                if(entry.getValue()>pq.peek()[1]){//当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                    pq.poll();//弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    pq.add(new int[]{entry.getKey(),entry.getValue()});
                }
            }
        }
        int[] ans = new int[k];
        for (int i = k-1; i >=0; i--) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
    //简化
    public int[] topKFrequent03(int[] nums, int k) {
        // 优先级队列，为了避免复杂 api 操作，pq 存储数组
        // lambda 表达式设置优先级队列从大到小存储 o1 - o2 为从小到大，o2 - o1 反之
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] res = new int[k]; // 答案数组为 k 个元素
        Map<Integer, Integer> map = new HashMap<>(); // 记录元素出现次数
        for(int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        for(Map.Entry<Integer,Integer> x : map.entrySet()) { // entrySet 获取 k-v Set 集合
            // 将 kv 转化成数组
            int[] tmp = new int[2];
            tmp[0] = x.getKey();
            tmp[1] = x.getValue();
            pq.offer(tmp);
            // 下面的代码是根据小根堆实现的，我只保留优先队列的最后的k个，只要超出了k我就将最小的弹出，剩余的k个就是答案
            if(pq.size() > k) {
                pq.poll();
            }
        }
        for(int i = 0; i < k; i ++) {
            res[i] = pq.poll()[0]; // 获取优先队列里的元素
        }
        return res;
    }
}
