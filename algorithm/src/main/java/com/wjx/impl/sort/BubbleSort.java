package com.wjx.impl.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Gin
 * @description
 * @date 2024/1/7 23:18
 */
public class BubbleSort {
    public static void main(String[] args) {
        //定义数组
        int[] array = {3, 4, 5, 6, 7};
        System.out.println("排序前的内容:" + Arrays.toString(array));
        for (int j = 0; j < array.length-1;j++){ //外层循环,趟数
            //j:0,1,2,3
            //i = 0:表示每次都从索引为0的开始,向后两两比较
            for (int i = 0; i < array.length-1-j;i++){
                //内层循环,每趟执行的次数,‐1为了防止索引越界,‐j为了提高效率
                if (array[i] < array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        System.out.println("排序后的内容:" + Arrays.toString(array));
    }

}

