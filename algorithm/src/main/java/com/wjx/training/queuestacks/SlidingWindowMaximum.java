package com.wjx.training.queuestacks;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <h1><a href="https://leetcode.cn/problems/sliding-window-maximum/">239. 滑动窗口最大值</a></h1>
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。<br>
 * 你只可以看到在滑动窗口内的 k 个数字。<br>
 * 滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <br>
 * <h2>示例 1:</h2>
 * <pre>
 * <b>输入：</b>nums = [1,3,-1,-3,5,3,6,7], k = 3
 * <b>输出：</b>[3,3,5,5,6,7]
 * <b>解释：</b>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       <strong>3</strong>
 *  1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 *  1  3 [-1  -3  5] 3  6  7       <strong>5</strong>
 *  1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 *  1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 *  1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
 * </pre>
 * <h2>示例 2:</h2>
 * 输入：nums = [1], k = 1<br>
 * 输出：[1]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= nums.length <= 10<sup>5</sup></li>
 * <li>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></li>
 * <li>1 <= k <= nums.length</li>
 *
 * @author Gin
 * @description
 * @date 2024/1/3 17:10
 */
public class SlidingWindowMaximum {

    //解法一
    //自定义数组
    static class MyQueue {
        Deque<Integer> deque = new LinkedList<>();
        //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
        //同时判断队列当前是否为空
        void poll(int val) {
            if (!deque.isEmpty() && val == deque.peek()) {
                deque.poll();
            }
        }
        //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
        //保证队列元素单调递减 保证last是最大的
        //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
        void add(int val) {
            while (!deque.isEmpty() && val > deque.getLast()) {
                deque.removeLast();
            }
            deque.add(val);
        }
        //队列队顶元素始终为最大值
        int peek() {
            return deque.peek();
        }
    }

    static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 1) {
                return nums;
            }
            int len = nums.length - k + 1;
            //存放结果元素的数组
            int[] res = new int[len];
            int num = 0;
            //自定义队列
            MyQueue myQueue = new MyQueue();
            //先将前k的元素放入队列
            for (int i = 0; i < k; i++) {
                myQueue.add(nums[i]);
            }
            res[num++] = myQueue.peek();
            for (int i = k; i < nums.length; i++) {
                //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
                myQueue.poll(nums[i - k]);
                //滑动窗口加入最后面的元素
                myQueue.add(nums[i]);
                //记录对应的最大值
                res[num++] = myQueue.peek();
            }
            return res;
        }
    }


    public static void main(String[] args) {
//        SlidingWindowMaximum maximum = new SlidingWindowMaximum();
//        maximum.maxSlidingWindow(new int[]{1,3,1,2,0,5},3);
        Solution solution = new Solution();
        solution.maxSlidingWindow(new int[]{1,3,1,2,0,5},3);
    }

    //解法二
    //利用双端队列手动实现单调队列
    /**
     * 用一个单调队列来存储对应的下标，每当窗口滑动的时候，直接取队列的头部指针对应的值放入结果集即可
     * 单调队列类似 （tail -->） 3 --> 2 --> 1 --> 0 (--> head) (右边为头结点，元素存的是下标)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        //存下标
        ArrayDeque<Integer> integers = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            //不在下标范围内则抛出
            while (!integers.isEmpty() && integers.peek() < i - k + 1) {
                integers.poll();
            }
            //尾部最大
            while (!integers.isEmpty() && nums[integers.peekLast()] < nums[i]) {
                integers.pollLast();
            }
            integers.offer(i);
            if (i >= k - 1 ) {
                result[i - k + 1] = nums[integers.peek()];
            }
        }
        return result;
    }
}
