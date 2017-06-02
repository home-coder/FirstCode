package com.example;

//正因为没有名字，所以匿名内部类只能使用一次，通常用来简化代码缩写
//使用匿名内部类必须有个前提，那就是必须继承一个父类或者实现一个接口
public class MyClass {
    public static void main(String args[]){
//0.
        HPPrinter hpPrinter = new HPPrinter();
        Printer printer = hpPrinter;
        printer.print();
        Printer printer1 = new HPPrinter();
        printer1.print();

//0.1 继承父类
        Printer printer2 = new Printer() {
            @Override
            public void print() {
                System.out.println("匿名内部类");
            }
        };
        printer2.print();
//1. 实现接口
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.print("hello java");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

//1.1如果不用匿名内部类，那么就要定义一个类来实现Runnable接口
       //public 局部内部类，定义在一个方法内，这就类似局部变量，不能使用public protected private static等。
       class MyThread implements Runnable {
           @Override
            public void run() {
               System.out.print("hello mythread");
           }
        }
        Thread thread1 = new Thread(new MyThread());
        thread1.start();

//2.实现接口
/* 这种发发最为常见， 里面的参数是匿名内部类*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("hello java");
            }
        }).start();

//3.
        new TestClass().run();//测试只使用一次的类的方法。
        new HPPrinter().print();
//4.
        Person person = new Person() {
            public void eat() {
                System.out.print("person eat");
            }
        };
        person.eat();
    }
}
