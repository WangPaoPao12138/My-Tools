package com.wjx.training.hashtable;

import java.util.HashSet;

/**
 * <h1><a href="https://leetcode.cn/problems/happy-number/">202. 快乐数</a></h1>
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。<br>
 * 「快乐数」 定义为：<br>
 * <li>对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。</li>
 * <li>然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。</li>
 * <li>如果这个过程 结果为 1，那么这个数就是快乐数。</li>
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。<br>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：n = 19<br>
 * 输出：true<br>
 * 解释：<br>
 * 1<sup>2</sup> + 9<sup>2</sup> = 82<br>
 * 8<sup>2</sup> + 2<sup>2</sup> = 68<br>
 * 6<sup>2</sup> + 8<sup>2</sup> = 100<br>
 * 1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1<br>
 * 输出: true<br>
 * <h2>示例 2:</h2>
 * 输入：n = 2<br>
 * 输出：false<br>
 * <p>
 * 提示：<br>
 * <li>1 <= n <= 2<sup>31</sup> - 1</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/17 19:33
 */
public class HappyNumber {
    //HashSet
    public boolean isHappy01(int n) {
        HashSet<Integer> usedNumber = new HashSet<>();
        while (!usedNumber.contains(n) && n > 0) {
            usedNumber.add(n);
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            n = sum;
        }
        return usedNumber.contains(1);
    }

    //快慢指针
    public boolean isHappy02(int n) {
        //因为为了保证双指针不同不为循环，所以fast先跑一步
        int fast = getNext(n);
        int slow = n;
        while (fast != slow && fast != 1) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;
    }
}
