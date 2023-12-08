package com.wjx.design.pattern.singleton;

/**
 * <h1>饿汉式</h1>
 * 这种方式在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快。 <br>
 * 这种方式基于类加载机制避免了多线程的同步问题，<br>
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，<br>
 * 这时候初始化instance显然没有达到懒加载的效果。
 *
 * @author Gin
 * @description
 * @date 2023/12/8 17:15
 */
public class EHan {
    private static EHan singleton = new EHan();

    public EHan() {
    }

    public static EHan getSingleton() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        return singleton;
    }

    static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println(EHan.getSingleton().hashCode());
        }

        public static void main(String[] args) {
            MyThread[] mts = new MyThread[10];
            for(int i = 0 ; i < mts.length ; i++){
                mts[i] = new MyThread();
            }

            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }

}
