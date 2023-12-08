package com.wjx.design.pattern.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>使用容器实现单例模式</h1>
 * 用SingletonManager 将多种的单例类统一管理，在使用时根据key获取对象对应类型的对象。
 * 这种方式使得我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，
 * 降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。
 *
 * @author Gin
 * @description
 * @date 2023/12/8 23:53
 */
public class SingletonManager {
    //这里要用 ConcurrentHashMap 防止多线程对于 map操作出现的问题 HashMap线程不安全
    private static Map<String, Object> objMap = new ConcurrentHashMap<>();

    private SingletonManager() {
    }

    public static void registerService(String key, Object instance) {
        objMap.computeIfAbsent(key, value -> instance);
    }

    public static Object getService(String key) {
        return objMap.get(key);
    }
}
