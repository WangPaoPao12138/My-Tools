package com.wjx.design.pattern.factory;

/**
 * <h1>抽象工厂模式</h1>
 * <strong>定义：</strong>定义了一个接口用于创建相关或有依赖关系的对象族，而无需明确指定具体类。<br>
 * <p>
 * 总结一下三种模式：
 * <p>
 *  例子为 上海 北京工厂
 * 简单工厂模式就是建立一个实例化对象的类，在该类中对多个对象实例化。 新增其他产品就需要修改代码 新增产品A，B，C等
 * 工厂方法模式是定义了一个创建对象的抽象方法，由子类决定要实例化的类。 这样做的好处是再有新的类型的对象需要实例化只要增加子类即可。 例如 四川工厂+四川的产品
 * 抽象工厂模式定义了一个接口用于创建对象族，而无需明确指定具体类。//上海产品对象族  北京产品对象族  对象族里面有产品A/B/C
 * 抽象工厂也是把对象的实例化交给了子类，即支持拓展。同时提供给客户端接口，避免了用户直接操作子类工厂。
 *
 * @author Gin
 * @description
 * @date 2023/12/10 23:34
 */
public class AbstractFactory {
    //
    public static void main(String[] args) {
        System.out.println("------------上海产品------------");
        ShangHaiFactory shangHaiFactory = new ShangHaiFactory();
        shangHaiFactory.getAProduct("A").output();
        shangHaiFactory.getAProduct("B").output();
        shangHaiFactory.getBProduct("A").output();
        shangHaiFactory.getBProduct("B").output();
        System.out.println("------------北京产品------------");
        BeiJingFactory beiJingFactory = new BeiJingFactory();
        beiJingFactory.getAProduct("A").output();
        beiJingFactory.getAProduct("B").output();
        beiJingFactory.getBProduct("A").output();
        beiJingFactory.getBProduct("B").output();
    }

    interface AbsFactory {
        /**
         * 生产A
         *
         * @param productType
         * @return
         */
        AProduct getAProduct(String productType);

        /**
         * 生产B
         *
         * @param productType
         * @return
         */
        BProduct getBProduct(String productType);
    }

    static class BeiJingFactory implements AbsFactory {
        @Override
        public AProduct getAProduct(String productType) {
            if ("A".equals(productType)) return new BeiJingAProduct1();
            if ("B".equals(productType)) return new BeiJingAProduct2();
            return null;
        }

        @Override
        public BProduct getBProduct(String productType) {
            if ("A".equals(productType)) return new BeiJingBProduct1();
            if ("B".equals(productType)) return new BeiJingBProduct2();
            return null;
        }
    }

    static class ShangHaiFactory implements AbsFactory {
        @Override
        public AProduct getAProduct(String productType) {
            if ("A".equals(productType)) return new ShangHaiAProduct1();
            if ("B".equals(productType)) return new ShangHaiAProduct2();
            return null;
        }

        @Override
        public BProduct getBProduct(String productType) {
            if ("A".equals(productType)) return new ShangHaiBProduct1();
            if ("B".equals(productType)) return new ShangHaiBProduct2();
            return null;
        }
    }

    /**
     * A产品族
     */
    static abstract class AProduct {
        public void output() {
            System.out.println("产品族A");
        }
    }

    /**
     * B产品族
     */
    static abstract class BProduct {
        public void output() {
            System.out.println("产品族B");
        }
    }

    /**
     * 北京产品A1
     */
    static class BeiJingAProduct1 extends AProduct {
        public void output() {
            System.out.println("BeiJing产品A1");
        }
    }

    /**
     * 北京产品A2
     */
    static class BeiJingAProduct2 extends AProduct {
        public void output() {
            System.out.println("BeiJing产品A2");
        }
    }

    /**
     * 北京产品B1
     */
    static class BeiJingBProduct1 extends BProduct {
        public void output() {
            System.out.println("BeiJing产品B1");
        }
    }

    /**
     * 北京产品B2
     */
    static class BeiJingBProduct2 extends BProduct {
        public void output() {
            System.out.println("BeiJing产品B2");
        }
    }

    /**
     * 上海产品A1
     */
    static class ShangHaiAProduct1 extends AProduct {
        public void output() {
            System.out.println("ShangHai产品A1");
        }
    }

    /**
     * 上海产品A2
     */
    static class ShangHaiAProduct2 extends AProduct {
        public void output() {
            System.out.println("ShangHai产品A2");
        }
    }

    /**
     * 上海产品B
     */
    static class ShangHaiBProduct1 extends BProduct {
        public void output() {
            System.out.println("ShangHai产品B1");
        }
    }

    /**
     * 上海产品B
     */
    static class ShangHaiBProduct2 extends BProduct {
        public void output() {
            System.out.println("ShangHai产品B2");
        }
    }

}
