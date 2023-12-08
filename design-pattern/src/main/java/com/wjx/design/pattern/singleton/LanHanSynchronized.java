package com.wjx.design.pattern.singleton;

/**
 * <h1> 懒汉模式（线程安全）- 本类主要围绕synchronized来说明</h1>
 * 这种写法能够在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，造成不必要的同步开销，<br>
 * 而且大部分时候我们是用不到同步的，所以不建议用这种模式。
 * <p></p>
 * <li>01 &nbsp;&nbsp; {@link #getSingleton01()} 在getSingleton上加锁,线程安全 效率低</li>
 * <li>02 &nbsp;&nbsp; {@link #getSingleton02()} 同步代码块加锁方法内代码,线程安全 效率低</li>
 * <li>03 &nbsp;&nbsp; {@link #getSingleton03()} 同步代码块只加锁实例化关键代码.效率高,但是线程不安全,
 * 如果要线程安全可以看{@link LanHanDoubleCheckLocking#getSingleton()} 内代码进行优化</li>
 *
 * @author Gin
 * @description
 * @date 2023/12/8 17:50
 */
public class LanHanSynchronized {
    private static LanHanSynchronized singleton;

    private LanHanSynchronized() {
    }

    //每次锁get方法
    public static synchronized LanHanSynchronized getSingleton01() {
        if (singleton == null) {//懒汉式
            //这里假设在创建实例前有一些准备性的耗时工作要处理
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            singleton = new LanHanSynchronized();
        }
        return singleton;
    }

    //但01是这种实现方式的运行效率会很低。同步方法效率低，那我们考虑使用同步代码块来实现
    //这里的实现能够保证多线程并发下的线程安全性，但是这样的实现将全部的代码都被锁上了，同样的效率很低下。
    //锁
    public static LanHanSynchronized getSingleton02() {
        //粒度还是不够小
        synchronized (LanHanSynchronized.class) {
            if (singleton == null) {//懒汉式
                //这里假设在创建实例前有一些准备性的耗时工作要处理
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }
                singleton = new LanHanSynchronized();
            }
        }

        return singleton;
    }


    //但02是这种实现方式的运行效率会很低。这样的方法进行代码块同步，代码的运行效率是能够得到提升，但是却没能保住线程的安全性
    //锁
    public static LanHanSynchronized getSingleton03() {
        if (singleton == null) {//懒汉式
            //这里假设在创建实例前有一些准备性的耗时工作要处理
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            synchronized (LanHanSynchronized.class) {
                singleton = new LanHanSynchronized();
            }
        }
        return singleton;
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
//            System.out.println(LanHanThreadSafe01.getSingleton01().hashCode());
            System.out.println(LanHanSynchronized.getSingleton02().hashCode());
//            System.out.println(LanHanThreadSafe01.getSingleton03().hashCode());
        }

        public static void main(String[] args) {
            LanHanSynchronized.MyThread[] mts = new LanHanSynchronized.MyThread[10];
            for (int i = 0; i < mts.length; i++) {
                mts[i] = new LanHanSynchronized.MyThread();
            }

            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }
}
