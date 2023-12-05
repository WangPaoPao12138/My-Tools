package com.wjx.training.array;

/**
 * <h1><a href="https://leetcode.cn/problems/spiral-matrix-ii/">59. 螺旋矩阵 II</a></h1>
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <br>
 * <h2>示例 1:</h2>
 * 输入：n = 3<br>
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]<br>
 * <h2>示例 2:</h2>
 * 输入：n = 1<br>
 * 输出：[[1]]<br>
 * <p>
 * 提示：<br>
 * <li>1 <= n <= 20</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/4 22:43
 */
public class SpiralMatrixIi {
    public static void main(String[] args) {
        SpiralMatrixIi spiralMatrixIi = new SpiralMatrixIi();
        System.out.println(spiralMatrixIi.generateMatrix(3));
    }

    //模拟
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        //行列大小        //当前位置
        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        ///当前行列限制
        int index = 1;
        //左闭右开
        while (index <= n * n) {
            //右移
            for (int j = left; j <= right; j++) {
                res[up][j] = index++;
            }
            //上限降低
            up++;
            //下移
            for (int i = up; i <= down; i++) {
                res[i][right] = index++;
            }
            //右限降低
            right--;
            //左移
            for (int j = right; j >= left; j--) {
                res[down][j] = index++;
            }
            //下限降低
            down--;
            //上移
            for (int i = down; i >= up; i--) {
                res[i][left] = index++;
            }
            //左限降低
            left++;
        }
        return res;
    }
}
