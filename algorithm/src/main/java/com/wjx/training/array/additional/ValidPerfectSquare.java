package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/valid-perfect-square/">367. 有效的完全平方数</a></h1>
 * 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。<br>
 * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。<br>
 * 不能使用任何内置的库函数，如  sqrt 。
 * <br>
 * <h2>示例 1:</h2>
 * 输入: num = 16<br>
 * 输出: true<br>
 * 解释：返回 true ，因为 4 * 4 = 16 且 4 是一个整数。<br>
 * <h2>示例 2:</h2>
 * 输入: num = 14<br>
 * 输出: false<br>
 * 解释：返回 false ，因为 3.742 * 3.742 = 14 但 3.742 不是一个整数。<br>
 * <p>
 * 提示：<br>
 * <li>1 <= x <= 2<sup>31</sup> - 1</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/8 21:20
 */
public class ValidPerfectSquare {
    //内置库函数
    public boolean isPerfectSquare01(int num) {
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }

    //暴力 时间O(sqrt(N))
    public boolean isPerfectSquare02(int num) {
        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }

    //二分 时间O(logN)
    public boolean isPerfectSquare03(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            long sum = (long) middle * middle;
            if (sum == num) {
                return true;
            } else if (sum < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }
}
