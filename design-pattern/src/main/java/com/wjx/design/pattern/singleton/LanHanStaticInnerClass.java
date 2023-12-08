package com.wjx.design.pattern.singleton;

/**
 * <h1>静态内部类</h1>
 * <li>mybatis框架多处用到</li>
 * <p></p>
 * 第一次加载Singleton类时并不会初始化sInstance，只
 * 有第一次调用getInstance方法时虚拟机加载SingletonHolder 并初始化sInstance ，
 * 这样不仅能确保线程安全也能保证Singleton类的唯一性，所以推荐使用静态内部类单例模式
 *
 * @author Gin
 * @description
 * @date 2023/12/8 23:23
 */
public class LanHanStaticInnerClass {
    //内部类
    private static class InnerClass {
        private static final LanHanStaticInnerClass singleton = new LanHanStaticInnerClass();
    }

    private LanHanStaticInnerClass() {
    }

    public static LanHanStaticInnerClass getInstance() {
        return InnerClass.singleton;
    }
}
