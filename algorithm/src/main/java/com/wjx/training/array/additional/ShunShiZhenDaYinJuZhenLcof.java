package com.wjx.training.array.additional;

/**
 * <h1><a href="https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/">LCR 146. 螺旋遍历二维数组</a></h1>
 * 给定一个二维数组 array，请返回「螺旋遍历」该数组的结果。<br>
 * 螺旋遍历：从左上角开始，按照 向右、向下、向左、向上 的顺序 依次 提取元素，然后再进入内部一层重复相同的步骤，直到提取完所有元素。<br>
 * <br>
 * <h2>示例 1:</h2>
 * 输入：array = [[1,2,3],[8,9,4],[7,6,5]]<br>
 * 输出：[1,2,3,4,5,6,7,8,9]<br>
 * <h2>示例 2:</h2>
 * 输入：array  = [[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]<br>
 * 输出：[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]<br>
 * <p>
 * 提示：<br>
 * <li>0 <= array.length <= 100</li>
 * <li>0 <= array[i].length <= 100</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/11 20:58
 */
public class ShunShiZhenDaYinJuZhenLcof {
    public static void main(String[] args) {
        ShunShiZhenDaYinJuZhenLcof lcof = new ShunShiZhenDaYinJuZhenLcof();
        lcof.spiralArray(new int[][]{{2,3}});
    }
    public int[] spiralArray(int[][] array) {
        int height = array.length;
        int weight = height>0?array[0].length:0;
        int count = height * weight;
        int[] result = new int[count];

        int top = 0;
        int right = weight - 1;
        int left = 0;
        int down = height - 1;

        int curIdx = 0;
        while (curIdx < count) {
            //左->右
            for (int j = left; j <= right && curIdx < count; j++) {
                result[curIdx++] = array[top][j];
            }
            top++;
            //上->下
            for (int i = top; i <= down && curIdx < count; i++) {
                result[curIdx++] = array[i][right];
            }
            right--;
            //右->左
            for (int j = right; j >=left && curIdx < count; j--) {
                result[curIdx++] = array[down][j];
            }
            down--;
            //下->上
            for (int i = down; i >= top && curIdx < count; i--) {
                result[curIdx++] = array[i][left];
            }
            left++;

        }
        return result;
    }
}
