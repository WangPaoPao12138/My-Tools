package com.wjx.impl.lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * LRU Java 实现
 *
 * @author Gin
 * @description
 * @date 2023/11/13 17:44
 */
public class LRUImplByJava {


    //采用inheritance方式实现比较简单，而且实现了Map接口，
    // 在多线程环境使用时可以使用 Collections.synchronizedMap()方法实现线程安全操作
    static class LRUCache1<K, V> extends LinkedHashMap<K, V>{
        private final int MAX_CACHE_SIZE;

        //LinkedHashMap的一个构造函数，当参数accessOrder为true时，即会按照访问顺序排序，否则 FIFO
        //最近访问的放在最前，最早访问的放在后面
        public LRUCache1(int cacheSize) {
            super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
            MAX_CACHE_SIZE = cacheSize;
        }
        //LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据
        //我们要做的就是重写这个方法，当满足一定条件时删除老数据
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            //超过限制数据的时候进行删除
            return size() > MAX_CACHE_SIZE;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<K, V> entry : entrySet()) {
                sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
            }
            return sb.toString();
        }
    }

    //delegation方式实现更加优雅一些，但是由于没有实现Map接口，所以线程同步就需要自己搞定了
    static class LRUCache2<K, V> {
        private final int MAX_CACHE_SIZE;
        private final float DEFAULT_LOAD_FACTOR = 0.75f;
        LinkedHashMap<K, V> map;

        public LRUCache2(int cacheSize) {
            MAX_CACHE_SIZE = cacheSize;
            //根据cacheSize和加载因子计算hashmap的 capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容，
            int capacity = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1;
//            map = new LinkedHashMap(capacity, DEFAULT_LOAD_FACTOR, true) {
            map = new LinkedHashMap(capacity, DEFAULT_LOAD_FACTOR, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > MAX_CACHE_SIZE;
                }
            };
        }

        public synchronized void put(K key, V value) {
            map.put(key, value);
        }

        public synchronized V get(K key) {
            return map.get(key);
        }

        public synchronized void remove(K key) {
            map.remove(key);
        }

        public synchronized Set<Map.Entry<K, V>> getAll() {
            return map.entrySet();
        }

        public synchronized int size() {
            return map.size();
        }

        public synchronized void clear() {
            map.clear();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry entry : map.entrySet()) {
                sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
            }
            return sb.toString();
        }
    }

    // LRU Cache的链表+HashMap实现
    static class LRUCache3<K, V> {

        private final int MAX_CACHE_SIZE;
        //用来实现LRU
        private Entry first;
        private Entry last;

        //存值
        private HashMap<K, Entry<K, V>> hashMap;

        public LRUCache3(int cacheSize) {
            MAX_CACHE_SIZE = cacheSize;
            hashMap = new HashMap<K, Entry<K, V>>();
        }

        public void put(K key, V value) {
            Entry entry = getEntry(key);
            //如果不存在这个key则初始化
            if (entry == null) {
                //如果size大于最大数量 则移除最后一个对象
                if (hashMap.size() >= MAX_CACHE_SIZE) {
                    hashMap.remove(last.key);
                    removeLast();
                }
                entry = new Entry();
                entry.key = key;
            }
            entry.value = value;
            //此节点移到头位置
            moveToFirst(entry);
            hashMap.put(key, entry);
        }

        public V get(K key) {
            Entry<K, V> entry = getEntry(key);
            if (entry == null) return null;
            moveToFirst(entry);
            return entry.value;
        }

        //移除当前节点
        public void remove(K key) {
            Entry entry = getEntry(key);
            if (entry != null) {
                if (entry.pre != null) entry.pre.next = entry.next;
                if (entry.next != null) entry.next.pre = entry.pre;
                if (entry == first) first = entry.next;
                if (entry == last) last = entry.pre;
            }
            hashMap.remove(key);
        }

        //移到头
        private void moveToFirst(Entry entry) {
            //已经是头则返回
            if (entry == first) return;
            //将本节点前后相连 本节点取出
            if (entry.pre != null) entry.pre.next = entry.next;
            if (entry.next != null) entry.next.pre = entry.pre;
            if (entry == last) last = last.pre;
            //有一个为 null 则此处就一个节点
            if (first == null || last == null) {
                first = last = entry;
                return;
            }
            //本节点和 first 相连 并且将本节点放到头节点上
            entry.next = first;
            first.pre = entry;
            first = entry;
            entry.pre = null;
        }
        //移除最后的节点
        private void removeLast() {
            if (last != null) {
                last = last.pre;
                if (last == null) first = null;
                else last.next = null;
            }
        }

        private Entry<K, V> getEntry(K key) {
            return hashMap.get(key);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Entry entry = first;
            while (entry != null) {
                sb.append(String.format("%s:%s ", entry.key, entry.value));
                entry = entry.next;
            }
            return sb.toString();
        }

        //链表
        class Entry<K, V> {
            public Entry pre;
            public Entry next;
            public K key;
            public V value;
        }
    }

    public static void main(String[] args) throws Exception {
        //实际使用中这样写还是有些繁琐，更实用的方法时像下面这样写，省去了单独见一个类的麻烦
        final int lruCache1Size = 100;
        Map<String, String> lruCache1 = new LinkedHashMap<String, String>((int) Math.ceil(lruCache1Size / 0.75f) + 1, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > lruCache1Size;
            }
        };

        final int lruCache2Size = 5;
        LinkedHashMap<Integer, String> lruCache2 = new LinkedHashMap<Integer, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > lruCache2Size;
            }
        };
        //测试
        System.out.println("start...");

        lruCache1();
        lruCache2();
        lruCache3();
        lruCache4();

        System.out.println("over...");
    }


    static   void lruCache1() {
        System.out.println();
        System.out.println("===========================LRU LinkedHashMap(inheritance)实现===========================");
        LRUCache1<Integer, String> lru = new LRUCache1(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }


    static   <T> void lruCache2() {
        System.out.println();
        System.out.println("===========================LRU LinkedHashMap(delegation)实现===========================");
        LRUCache2<Integer, String> lru = new LRUCache2(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }

    static  void lruCache3() {
        System.out.println();
        System.out.println("===========================LRU 链表实现===========================");
        LRUCache3<Integer, String> lru = new LRUCache3(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }

    static  void lruCache4() {
        System.out.println();
        System.out.println("===========================FIFO LinkedHashMap默认实现===========================");
        final int cacheSize = 5;
        LinkedHashMap<Integer, String> lru = new LinkedHashMap<Integer, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > cacheSize;
            }
        };
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }


}
