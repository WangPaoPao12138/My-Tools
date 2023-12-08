package com.wjx.design.pattern.singleton;

/**
 * <h1>完善使用enum枚举实现单例模式</h1>
 * 对 {@link EnumSingleton} 代码的封装隐藏实现细节
 * <br>
 * <li>EnumSingleton.FACTORY.getInstance().hashCode()  <br></li>
 * <li>WrapperEnumSingleton.getInstance().hashCode() <br></li>
 * <p>
 * 减少一个枚举对象的获取只暴露方法
 *
 * @author Gin
 * @description
 * @date 2023/12/8 23:48
 */
public class WrapperEnumSingleton {
    private enum MyEnumSingleton {
        FACTORY;

        private MySingleton singleton;

        private MyEnumSingleton() {//枚举类的构造方法在类加载是被实例化
            singleton = new MySingleton();
        }

        public MySingleton getSingleton() {
            return singleton;
        }
    }

    public static MySingleton getInstance() {
        return MyEnumSingleton.FACTORY.getSingleton();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(WrapperEnumSingleton.getInstance().hashCode());
        }

        public static void main(String[] args) {
            MyThread[] mts = new MyThread[10];
            for (int i = 0; i < mts.length; i++) {
                mts[i] = new MyThread();
            }
            for (int j = 0; j < mts.length; j++) {
                mts[j].start();
            }
        }
    }
}
