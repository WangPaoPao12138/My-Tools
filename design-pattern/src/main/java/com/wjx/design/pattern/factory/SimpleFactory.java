package com.wjx.design.pattern.factory;

/**
 * <h1>简单工厂模式</h1>
 * <strong>定义：</strong>定义了一个创建对象的类，由这个类来封装实例化对象的行为。<br>
 * 简单工厂存在的问题与解决方法： 简单工厂模式有一个问题就是，类的创建依赖工厂类. <br>
 * 也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了开闭原则.<br>
 * <p></p>
 * <h2>所以，从设计角度考虑，有一定的问题，如何解决？</h2> {@link FactoryFunction}
 * 我们可以定义一个创建对象的抽象方法并创建多个不同的工厂类实现该抽象方法，<br>
 * 这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。<br>
 *
 * @author Gin
 * @description
 * @date 2023/12/9 22:47
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Factory factory = new Factory();
        factory.getProduct("A").output();
    }

    static class Factory {
        public Product getProduct(String productType) {
            Product product = null;
            if ("A".equals(productType)) product = new ProductA();
            if ("B".equals(productType)) product = new ProductB();
            if ("C".equals(productType)) product = new ProductC();
            return product;
        }
    }

    /**
     * 产品族
     */
    static abstract class Product {
        public void output() {
            System.out.println("产品族");
        }
    }

    /**
     * 产品A
     */
    static class ProductA extends Product {
        public void output() {
            System.out.println("产品A");
        }
    }

    /**
     * 产品B
     */
    static class ProductB extends Product {
        public void output() {
            System.out.println("产品B");
        }
    }

    /**
     * 产品C
     */
    static class ProductC extends Product {
        public void output() {
            System.out.println("产品C");
        }
    }
}
