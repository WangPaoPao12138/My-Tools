package com.wjx.training.array.additional;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1><a href="https://leetcode.cn/problems/spiral-matrix/">54. 螺旋矩阵</a></h1>
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。<br>
 * <br>
 * <h2>示例 1:</h2>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;"><br>
 * <strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]<br>
 * <strong>输出：</strong>[1,2,3,6,9,8,7,4,5]<br>
 * <h2>示例 2:</h2>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;"><br>
 * <strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]<br>
 * <strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]<br>
 * <p>
 * 提示：<br>
 * <li>m == matrix.length</li>
 * <li>n == matrix[i].length</li>
 * <li>1 <= m, n <= 10</li>
 * <li>-100 <= matrix[i][j] <= 100</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/11 19:56
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
//        spiralMatrix.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        spiralMatrix.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        //    j  top
        // i
        // left      right
        //      down
        int height = matrix.length;
        int weight = matrix[0].length;
        int top = 0;
        int down = height - 1;
        int left = 0;
        int right = weight - 1;
        int count = height * weight;
        ArrayList<Integer> result = new ArrayList<>();
        int cur = 1;
        while (cur <= count) {
            //顶部 左到右
            for (int j = left; j <= right && cur <= count; j++) {
                result.add(matrix[top][j]);
                cur++;
            }
            top++;
            //右部 上到下
            for (int i = top; i <= down && cur <= count; i++) {
                result.add(matrix[i][right]);
                cur++;
            }
            right--;
            //下部 右到左
            for (int j = right; j >= left && cur <= count; j--) {
                result.add(matrix[down][j]);
                cur++;
            }
            down--;
            //左部 下到上
            for (int i = down; i >= top && cur <= count; i--) {
                result.add(matrix[i][left]);
                cur++;
            }
            left++;
        }
        return result;
    }
}
