package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/sqrtx/">69. x 的平方根</a></h1>
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。<br>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。<br>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。<br>
 * <br>
 * <h2>示例 1:</h2>
 * 输入: x = 4<br>
 * 输出: 2<br>
 * <h2>示例 2:</h2>
 * 输入: x = 8<br>
 * 输出: 2<br>
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。<br>
 * <p>
 * 提示：<br>
 * <li>0 <= x <= 2<sup>31</sup> - 1</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/7 23:01
 */
public class SqrtX {

    //二分
    public int mySqrt01(int x) {
        int left = 0;//左边小于等于sqrtx
        int right = x;//右边大于 sqrtx
        while (left<=right){
            int middle = left + (right - left) / 2;
            //坑点 x 范围 先将第一个middle 转为long
            long sum = (long) middle * middle;
            if (sum > x){
                right = middle-1;
            }else {
                left = middle+1;
            }
        }
        //又加1了超过位置 需要-1
        return  left-1;
    }
    //函数
    public int mySqrt02(int x) {
        int result = (int)Math.sqrt(x);
        return  result;
    }
}
