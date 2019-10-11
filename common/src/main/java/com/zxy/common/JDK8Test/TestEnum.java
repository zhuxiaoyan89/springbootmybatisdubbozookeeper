package com.zxy.common.JDK8Test;

import java.util.Arrays;
import java.util.List;

public class TestEnum {
    public static void main(String[] args) {
        int i = 100_111_1_1;
        double d = 123_23.231_23;
        System.out.println(i);
        System.out.println(d);

        Seasons se = Seasons.SPRING;
        switch (se){
            case SPRING:
                System.out.println("春天");
                System.out.println(se.getIndex() + se.getMsg());
                break;
            case SUMMER:
                System.out.println("夏天");
                System.out.println(se.getIndex() + se.getMsg());
                break;
            case AUTUMN:
                System.out.println("秋天");
                System.out.println(se.getIndex() + se.getMsg());
                break;
            case WINTER:
                System.out.println("冬天");
                System.out.println(se.getIndex() + se.getMsg());
                break;
            default:
                System.out.println("不存在的季节");
        }
        
        
        List<String> list = Arrays.asList("a", "b", "c");
        for (String s : list) {

        }

        list.forEach(s -> System.out.println(s));
        String json = "{\"name\": \"aaa\",\"age\": 25}";
    }
}
