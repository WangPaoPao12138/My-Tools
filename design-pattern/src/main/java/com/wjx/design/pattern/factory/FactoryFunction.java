package com.wjx.design.pattern.factory;

/**
 * <h1>工厂方法模式</h1>
 * <strong>定义：</strong>定义了一个创建对象的抽象方法，由子类决定要实例化的类。工厂方法模式将对象的实例化推迟到子类。<br>
 *
 * <h2>工厂方法存在的问题与解决方法：</h2>
 * 客户端需要创建类的具体的实例。简单来说就是用户要订北京工厂的产品，他必须去北京工厂，想订上海工厂的产品，必须去上海工厂。 <br>
 * 当北京工厂和上海工厂发生变化了，用户也要跟着变化，这无疑就增加了用户的操作复杂性。
 * 为了解决这一问题，我们可以把工厂类抽象为接口，用户只需要去找默认的工厂提出自己的需求（传入参数），<br>
 * 便能得到自己想要产品，而不用根据产品去寻找不同的工厂，方便用户操作。这也就是我们接下来要说的抽象工厂模式。
 *
 * @author Gin
 * @description
 * @date 2023/12/9 23:05
 */
public abstract class FactoryFunction {
    public static void main(String[] args) {
        //需要什么产品就新建什么工厂
        //产品A 就是产品A抽象工厂 + 各种实现工厂 广东/深圳/四川 等等
        //产品B 就是产品B抽象工厂 + 各种实现工厂 广东/深圳/四川 等等
        ProductAFactory factory = new ShangHaiProductAFactory();
        factory.getProduct("A").output();
    }

    /**
     * 工厂抽象类，核心的工厂类不再负责所有的产品的创建，而是将具体的工作交给子类去做
     */
    interface ProductAFactory {
        ProductA getProduct(String productType);
    }

    /**
     * 上海工厂族
     */
    static class ShangHaiProductAFactory implements ProductAFactory {
        @Override
        public ProductA getProduct(String productType) {
            return new ShangHaiProductA();
        }
    }

    /**
     * 北京工厂族
     */
    static class BeiJingProductAFactory implements ProductAFactory {
        @Override
        public ProductA getProduct(String productType) {
            return new BeiJingProductA();
        }
    }

    /**
     * 产品族
     */
    static abstract class ProductA {
        public void output() {
            System.out.println("产品族");
        }
    }

    /**
     * 产品A
     */
    static class BeiJingProductA extends ProductA {
        public void output() {
            System.out.println("BeiJing产品A");
        }
    }


    /**
     * 产品A
     */
    static class ShangHaiProductA extends ProductA {
        public void output() {
            System.out.println("ShangHai产品A");
        }
    }



}
