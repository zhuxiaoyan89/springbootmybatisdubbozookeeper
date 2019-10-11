package com.zxy.common.JDK8Test;

import org.junit.jupiter.api.Test;

public class FunctionInterfaceTest {

    @Test
     public void testLambda() {
        func(new FunctionInterface() {
//             @Override
//             public void test() {
//                 System.out.println("Hello World!");
//             }

//            @Override
//            public void test(int param) {
//                System.out.println("Hello World!" + param);
//            }
                @Override
                public boolean test(int param) {
                    System.out.println("Hello World!" + param);
                    return true;
                }
        });

        //使用Lambda表达式代替上面的匿名内部类
//        func(() -> System.out.println("Hello World"));
//        func((int x) -> System.out.println("Hello World!" + x));
        func((int x) -> {
                System.out.println("Hello World!" + x);
                return true;
        });
     }


     private void func(FunctionInterface functionInterface) {
//         functionInterface.test();
         functionInterface.test(1);

     }

}
