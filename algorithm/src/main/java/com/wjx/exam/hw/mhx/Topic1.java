package com.wjx.exam.hw.mhx;

import java.util.*;

/**
 * @author Gin
 * @description
 * @date 2024/3/10 20:51
 */
public class Topic1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n
        int n = sc.nextInt();
        //slm多少秒不在队列中
        int[] slmEvent = new int[n];
        //k 秒 v slm个数
        TreeMap<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        //原始长度
        for (int i = 1; i <= n; i++) {
            int direct = sc.nextInt();
            if (direct == 1) {
                slmEvent[i] = n - i;
            } else {
                slmEvent[i] = i;
            }
        }
        //每秒个数
        for (int i = 0; i < slmEvent.length; i++) {
            map.put(slmEvent[i], map.getOrDefault(slmEvent[i], 0) + 1);
        }
        //当前秒数

    }
}
