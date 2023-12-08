package com.wjx.design.pattern.singleton;

import java.io.*;

/**
 * <h1>序列化与反序列化的单例模式实现</h1>
 * 有一种情况下他们会重新创建对象，那就是反序列化，
 * 将一个单例实例对象写到磁盘再读回来，从而获得了一个实例。反序列化操作提供了
 * {@link SerializableSingleton#readResolve()}方法，
 * 这个方法可以让开发人员控制对象的反序列化。
 *
 * @author Gin
 * @description
 * @date 2023/12/8 23:33
 */
public class SerializableSingleton implements Serializable {
    private static final long serialVersionUID = 1L;

    //内部类
    private static class InnerSerializableSingleton {
        private static SerializableSingleton singleton = new SerializableSingleton();
    }

    private SerializableSingleton() {
    }

    public static SerializableSingleton getSingleton() {
        return InnerSerializableSingleton.singleton;
    }

    //该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return InnerSerializableSingleton.singleton;
    }

    static class TestSaveAndReadForSingleton {
        /**
         * 2133927002<br>
         * 1828972342<br>
         * <br>
         * --------------------------<br>
         * 从结果中我们发现，序列号对象的hashCode和反序列化后得到的对象的hashCode值不一样，<br>
         * 说明反序列化后返回的对象是重新实例化的，单例被破坏了<br>
         * {@link #readResolve()} 方法保证序列化成功<br>
         * --------------------------<br>
         * <br>
         * 2133927002<br>
         * 调用了readResolve方法！<br>
         * 2133927002<br>
         *
         * @param args
         */
        public static void main(String[] args) {
            SerializableSingleton singleton = SerializableSingleton.getSingleton();
            File file = new File("SerializableSingleton.txt");
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos);
            ) {
                oos.writeObject(singleton);
                System.out.println(singleton.hashCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                SerializableSingleton rSingleton = (SerializableSingleton) ois.readObject();
                System.out.println(rSingleton.hashCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
