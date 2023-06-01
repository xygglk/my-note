package org.example;

@FunctionalInterface //函数式接口：接口里面只有一个抽象方法   new 这个接口的实现类可以使用lambda表达式  @FunctionalInterface注解可省略
interface Test {
    public void getTestInfo();

// 第二个方法加上就不属于函数式接口了
//    public void getTestInfo1();
}

/**
 * @author XYGG
 * @description TODO
 * @date 2023/6/1 15:21
 */
public class LambdaDemo {

    /**
     * lambda表达式：（参数列表） -> {代码}    函数式编程（函数编程思想）
     * 面向对象编程思想：
     * 强调的是【对象】，必须通过对象的形式来做一些事情，一般情况下会比较复杂。
     * <p>
     * 函数编程思想：
     * 函数需要得有输入量、输出量，使用输入量计算得到输出量，【拿什么东西做什么事】
     */
    public static void main(String[] args) {
        //Runnable接口示例：
        Thread thread = new Thread(() -> {
            //方法体的操作相当于实现Thread继承的接口Runnable的唯一一个abstract方法void run()
        });


        //()->{}相当于：Test test
        new TT(() -> {
            System.out.println("这里操作");

            //不能有返回值 因为该lambda表达式其实走的是Test接口的 void getTestInfo()方法
            //return true;
        });
        /*
         *     相
         *     当
         *     于
         */
        Test test = new Test() {//这里可以转成lambda表达式，若Test接口有多个方法，不可转（使用lambda表达式会不知道走哪个方法）
            @Override
            public void getTestInfo() {
                System.out.println("这里操作");
            }
            //@Override
            //public void getTestInfo1() {
            //}
        };
        new TT(test);
    }
}

class TT implements Test {//***实现了函数式接口，则可以使用lambda表达式***

    TT(Test test) {
        //走的是这里
    }

    TT(boolean test) {

    }

    @Override
    public void getTestInfo() {

    }
}