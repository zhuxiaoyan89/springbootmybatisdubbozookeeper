package com.zxy.common.JDK8Test;

import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list1 = Arrays.asList("1", "2", "3");
        List list2 = Arrays.asList("3", "4", "5");

//        list1.add("4");

        boolean flag = list1.retainAll(list2);

        System.out.println(flag);
        System.out.println(list1.size());

        list1.forEach(s -> System.out.println(s));

    }
}
