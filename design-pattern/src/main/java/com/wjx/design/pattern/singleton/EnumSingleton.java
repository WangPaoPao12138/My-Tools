package com.wjx.design.pattern.singleton;

/**
 * <h1>枚举单例</h1>
 * 默认枚举实例的创建是线程安全的，并且在任何情况下都是单例，
 * <br>
 * <p>
 * {@link WrapperEnumSingleton} <br>
 * 将本类封装不暴露实现细节 ->EnumSingleton.FACTORY.getInstance().hashCode()
 *
 * @author Gin
 * @description
 * @date 2023/12/8 23:27
 */
public enum EnumSingleton {
    FACTORY;
    private MySingleton instance;

    EnumSingleton() {//枚举类的构造方法在类加载是被实例化
        instance = new MySingleton();
    }

    public MySingleton getInstance() {
        return instance;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(EnumSingleton.FACTORY.getInstance().hashCode());
        }

        public static void main(String[] args) {
            EnumSingleton.MyThread[] mts = new EnumSingleton.MyThread[10];
            for (int i = 0; i < mts.length; i++) {
                mts[i] = new EnumSingleton.MyThread();
            }
            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }
}
