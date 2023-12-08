package com.wjx.design.pattern.singleton;

/**
 * <h1>懒汉式 （线程不安全）</h1>
 * 懒汉模式申明了一个静态对象，在用户第一次调用时初始化，<br>
 * 虽然节约了资源，但第一次加载时需要实例化，反映稍慢一些，<br>
 * 而且在多线程不能正常工作。，
 *
 * @author Gin
 * @description
 * @date 2023/12/8 17:20
 */
public class LanHanUnThreadSafe {
    private static LanHanUnThreadSafe singleton;

    private LanHanUnThreadSafe() {

    }

    public static LanHanUnThreadSafe getSingleton() {
        //每次创建
        if (singleton == null) {//懒汉式
            //这里假设在创建实例前有一些准备性的耗时工作要处理
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            singleton = new LanHanUnThreadSafe();
        }
        return singleton;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(LanHanUnThreadSafe.getSingleton().hashCode());
        }

        public static void main(String[] args) {
            LanHanUnThreadSafe.MyThread[] mts = new LanHanUnThreadSafe.MyThread[10];
            for (int i = 0; i < mts.length; i++) {
                mts[i] = new LanHanUnThreadSafe.MyThread();
            }

            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }
}
