package com.wjx.exam.hw.mhx;

import java.util.*;

/**
 * @author Gin
 * @description
 * @date 2024/3/10 21:22
 */
public class Topic2 {


    public static void main(String[] args) {
        ArrayList<Integer> counts = new ArrayList<>();
        TreeMap<String, Integer> result = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        //队列人数
        LinkedList<String> peoples = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int limit = sc.nextInt();
        int eventCount = sc.nextInt();
        for (int i = 0; i < eventCount; i++) {
            int eventType = sc.nextInt();
            if (peoples.size() > 0 && total <= limit) {
                String first = peoples.removeFirst();
                result.put(first, result.getOrDefault(first, 0) + total);
                break;
            } else {
                switch (eventType) {
                    case 3:
                        int count = sc.nextInt();
                        String first = peoples.removeFirst();
                        result.put(first, result.getOrDefault(first, 0) + count);
                        break;
                    case 1:
                        String inName = sc.next().trim();
                        peoples.addLast(inName);
                        result.put(inName, result.getOrDefault(inName, 0) + 0);
                        break;
                    case 2:
                        String outName = sc.next().trim();
                        peoples.remove(outName);
                        break;
                    case 4:
                        counts.add(peoples.size());
                        break;
                }
            }
        }
        for (int i = 0; i < counts.size(); i++) {
            System.out.println(counts.get(i));
        }
        for (String name : result.keySet()) {
            System.out.println(name + " " + result.get(name));
        }
    }
}
