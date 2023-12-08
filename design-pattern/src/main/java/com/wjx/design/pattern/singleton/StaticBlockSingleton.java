package com.wjx.design.pattern.singleton;

/**
 * <h1>使用static代码块实现单例</h1>
 * 静态代码块中的代码在使用类的时候就已经执行了，所以可以应用静态代码块的这个特性的实现单例设计模式。
 *
 * @author Gin
 * @description
 * @date 2023/12/8 23:42
 */
public class StaticBlockSingleton {
    private static StaticBlockSingleton singleton = null;

    private StaticBlockSingleton() {
    }

    static {
        singleton = new StaticBlockSingleton();
    }

    public static StaticBlockSingleton getSingleton() {
        return singleton;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(StaticBlockSingleton.getSingleton().hashCode());
            }
        }

        public static void main(String[] args) {
            MyThread[] mts = new MyThread[3];
            for (int i = 0; i < mts.length; i++) {
                mts[i] = new MyThread();
            }
            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }
}
