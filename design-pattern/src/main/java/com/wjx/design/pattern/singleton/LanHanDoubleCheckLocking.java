package com.wjx.design.pattern.singleton;

/**
 * <h1>双重检查锁</h1>
 * 这种写法在getSingleton方法中对singleton进行了两次判空，
 * 第一次是为了不必要的同步，第二次是在singleton等于null的情况下才创建实例。
 * 在这里用到了volatile关键字，不了解volatile关键字的可以查看
 * <a href="https://blog.csdn.net/itachi85/article/details/50274169"><h2>《Java多线程（三）volatile域》</h2></a>这篇文章，
 * 在这篇文章我也提到了双重检查模式是正确使用volatile关键字的场景之一。
 * <p></p>
 * 在这里使用volatile会或多或少的影响性能，但考虑到程序的正确性，牺牲这点性能还是值得的。
 * DCL优点是资源利用率高，第一次执行getInstance时单例对象才被实例化，效率高。
 * 缺点是第一次加载时反应稍慢一些，在高并发环境下也有一定的缺陷，虽然发生的概率很小。
 * DCL虽然在一定程度解决了资源的消耗和多余的同步，线程安全等问题，但是他还是在某些情况会出现失效的问题，也就是DCL失效，
 * <a href="https://blog.csdn.net/wuzhiwei549/article/details/80006533"><h2>《DCL失效原因和解决方案》</h2></a>
 * 在《Java并发编程实践》一书建议用静态内部类单例模式来替代DCL。
 * <br>
 * <a href="https://blog.csdn.net/qq_39288456/article/details/112425223">Java ~ 双重检查锁（DCL）的原理与失效原因</a><br>
 * <a href="https://icepip.blog.csdn.net/article/details/132989903?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-2-132989903-blog-80006533.235%5Ev39%5Epc_relevant_anti_vip&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-2-132989903-blog-80006533.235%5Ev39%5Epc_relevant_anti_vip&utm_relevant_index=5">
 * 高并发下双重检测锁DCL指令重排问题剖析</a>
 *
 * @author Gin
 * @description
 * @date 2023/12/8 22:58
 */
public class LanHanDoubleCheckLocking {
    //volatile 关键字
    volatile private static LanHanDoubleCheckLocking singleton;

    private LanHanDoubleCheckLocking() {
    }

    public static LanHanDoubleCheckLocking getSingleton() {
        if (singleton == null) {
            synchronized (LanHanDoubleCheckLocking.class) {
                //创建实例之前可能会有一些准备性的耗时工作
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }
                if (singleton == null) {
                    singleton = new LanHanDoubleCheckLocking();
                }
            }

        }
        return singleton;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(LanHanDoubleCheckLocking.getSingleton().hashCode());
        }

        public static void main(String[] args) {
            LanHanDoubleCheckLocking.MyThread[] mts = new LanHanDoubleCheckLocking.MyThread[10];
            for (int i = 0; i < mts.length; i++) {
                mts[i] = new LanHanDoubleCheckLocking.MyThread();
            }
            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }
}
