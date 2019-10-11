package com.zxy.common.JDK8Test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://www.iteye.com/blog/sihailoveyan-2444050
 */
public class StreamLearning {

    /**
     * 创建流
     */
    @Test
    public void testCreateStream() {
        //利用Stream.of方法创建流
        Stream<String> stream = Stream.of("hello", "world", "java8");
        stream.forEach(System.out::println);
        System.out.println("########################");

        //利用Stream.iterate方法创建流
        Stream.iterate(10 ,n -> n+1).limit(5).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("##################");

        //利用Stream.generate方法创建流
        Stream.generate(Math::random).limit(5).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("##################");

        //从现有的集合中创建流
        List<String> strings = Arrays.asList("hello", "world", "Java8");
        strings
//                .stream().collect(Collectors.toList())
                .forEach(System.out::println);
        String string = strings.stream().collect(Collectors.joining(","));
        System.out.println(string);
    }

    /**
     * 装箱流
     * 在处理对象流的时候，可以利用 Collectors 类的静态方法转换为集合，例如，将字符串流转换为  List<String> ，这种方式是没有问题的。
     * 如果遇到 double流想要转换为 List 时，这是就会报错。
     */
    @Test
    public void testBoxedStream(){

        // 方式一
        // 利用 boxed 方法，可以将 DoubleStream 转换为 Stream<Double>
        DoubleStream.of(1.0, 2.0, 3.0)
                .boxed()
                .collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("##################");

        // 方式二
        // 利用 mapToObj 方法也可以实现上面的功能，
        // 另外，也提供了  mapToInt、mapToLong、mapToDouble 等方法将基本类型流转换为相关包装类型。
        DoubleStream.of(1.0, 2.0, 3.0)
                .mapToObj(Double::valueOf)
                .collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("##################");

        // 方式三
        // 第一个参数：使用一个静态方法初始化一个 List 容器，第二个参数：使用静态方法 add ，添加元素，第三个参数：使用静态方法 addAll ，用于联合所有的元素。
        List<Double> list = DoubleStream.of(1.0, 2.0, 3.0)
                .collect(ArrayList<Double>::new, ArrayList::add, ArrayList::addAll);
//        list.stream().forEach(System.out::println);
        list.forEach(System.out::println);

    }

    /**
     * 字符串与流之间的转换
     * 将 String 转为流有两种方法，分别是  java.lang.CharSequence 接口定义的默认方法 chars 和 codePoints ，而将流转为字符串就是我们前面已经讲解到的方法 collect 。
     */
    @Test
    public void testStringToStream(){
        String s = "hello world Java8".codePoints()//转换成流
                .collect(StringBuffer::new,
                        StringBuffer::appendCodePoint,
                        StringBuffer::append)//将流转换为字符串
                .toString();

        System.out.println("hello world Java8".codePoints());
        System.out.println(s);

        String s1 = "hello world Java8".chars()//转换成流
                .collect(StringBuffer::new,
                        StringBuffer::appendCodePoint,
                        StringBuffer::append)//将流转换为字符串
                .toString();
        System.out.println("hello world Java8".chars());
        System.out.println(s1);
    }

    /**
     * 流的映射 map 与 flatMap
     * 流的映射是什么意思呢，我们先将一个在 Java8 之前的例子，我们常常需要将一个集合的对象的某一个字段取出来，然后再存到另外一个集合中
     */
    @Test
    public void testList() {
        List<Person> list = new ArrayList<>();
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Java5"));
        friends.add(new Friend("Java6"));
        friends.add(new Friend("Java7"));
        Person person = new Person();
        person.setFriends(friends);
        list.add(person);

        List<String> strings = new ArrayList<>();

        for(Person p : list){
            strings.add(p.getName());
        }

        List<String> names = new ArrayList<>();
        for (Friend friend : friends) {
            names.add(friend.getName());
        }

        names.forEach(System.out::println);
    }

    @Test
    public void testMapAndFlatMap() {
        List<Person> list = new ArrayList<>();
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Java5"));
        friends.add(new Friend("Java6"));
        friends.add(new Friend("Java7"));
        Person person = new Person();
        person.setFriends(friends);
        list.add(person);

        //映射出名字
        List<String> strings = list.stream().map(Person::getName).collect(Collectors.toList());
        strings.forEach(System.out::println);

        List<String> names = friends.stream().map(Friend::getName).collect(Collectors.toList());
        names.forEach(System.out::println);

        // 返回值是 List<List<Friend>>，这种形式集合里面还包着集合，处理有点麻烦，但是，不是还有另外 flatMap 没有使用吗，这个方法正好能够解决这个问题。
        List<List<Friend>> collect = list.stream().map(Person::getFriends).collect(Collectors.toList());
        // 这个方法的返回值是 List<Friend>，正如我们看到的，flatMap 的方法能够“展平”包裹的流，这就是 map 和 flatMap 的区别。
        List<Friend> collect1 = list.stream().flatMap(per -> per.getFriends().stream()).collect(Collectors.toList());
    }

    public class Friend{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Friend(String name) {
            this.name = name;
        }
    }

    public class Person{
        private String name;
        private List<Friend> friends;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Friend> getFriends() {
            return friends;
        }

        public void setFriends(List<Friend> friends) {
            this.friends = friends;
        }
    }

    /**
     * 流的连接有两种方式，如果是两个流的连接，使用 Stream.concat 方法，如果是三个及三个以上的流的连接，就使用 Stream.flatMap 方法。
     */
    @Test
    public void testConcatStream(){
        //两个流的连接
        Stream<String> first = Stream.of("sihai", "sihai2", "sihai3");
        Stream<String> second = Stream.of("sihai4", "sihai5", "sihai6");
        Stream<String> third = Stream.of("siha7", "sihai8", "sihai9");
        Stream<String> concat = Stream.concat(first, second);

        //多个流的连接
        Stream<String> stringStream = Stream.of(first, second, third).flatMap(Function.identity());

//        concat.forEach(System.out::println);
        stringStream.forEach(System.out::println);
    }

    /**
     * 流的规约操作
     */
    /**
     * 基本类型流都有内置的规约操作。包括average、count、max、min、sum、summaryStatistics
     * 前面的几个方法相信不用说了，summaryStatistics 方法是前面的几个方法的结合，下面我们看看他们如何使用。
     */
    @Test
    public void testReduce1() {
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        long count = Arrays.stream(strings)
                .map(String::length)
                .count();
        System.out.println(count);

        System.out.println("##################");

        int sum = Arrays.stream(strings)
                .mapToInt(String::length)
                .sum();
        System.out.println(sum);

        System.out.println("##################");

        OptionalDouble average = Arrays.stream(strings)
                .mapToInt(String::length)
                .average();
        System.out.println(average);

        System.out.println("##################");

        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();
        System.out.println(max);

        System.out.println("##################");

        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        System.out.println(min);

        System.out.println("##################");

        DoubleSummaryStatistics statistics = DoubleStream.generate(Math::random)
                .limit(1000)
                .summaryStatistics();
        System.out.println(statistics);
    }

    /**
     * 基本的规约操作
     * 基本的规约操作是利用前面讲过的 reduce 方法实现的，IntStream 接口定义了三种 reduce 方法的重载形式，如下；
     *
     * OptionalInt reduce(IntBinaryOperator op)
     *
     * int reduce(int identity, IntBianryOperator op)
     *
     * <U> U reduce(U identity,
     *       BiFunction<U,? super T,U> accumulator,
     *       BianryOperator<U> combiner)
     * 上面的 identity 参数就是初始化值的意思，IntBianryOperator 类型的参数就是操作，例如  lambda 表达式；BianryOperator<U> combiner是一个组合器，在前面有讲过。
     */
    @Test
    public void testReduce2() {
        int sum = IntStream.range(1, 20)
                .reduce((x, y) -> x + y)
                .orElse(0);
        System.out.println(sum);

        System.out.println("##################");

        int sum2 = IntStream.range(1, 20)
                .reduce(0, (x, y) -> x + 2 * y);
        System.out.println(sum2);

        System.out.println("##################");

        int sum3 = IntStream.range(1, 20)
                .reduce(0, Integer::sum);
        System.out.println(sum3);

    }


    /**
     * 流的计数
     * 流的数量统计有两种方法，分别是 Stream.count() 方法和 Collectors.counting() 方法。
     */
    @Test
    public void testStatistics() {
        //统计数量
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        long count = Arrays.stream(strings)
                .count();
        System.out.println(count);

        System.out.println("##################");

        Long count2 = Arrays.stream(strings)
                .collect(Collectors.counting());
        System.out.println(count2);

    }

    /**
     * 流的查找 Stream 接口提供了两个方法 findFirst 和 findAny。
     *
     * findFirst 方法返回流中的第一个元素的 Optional，而 findAny 方法返回流中的某个元素的  Optional。
     *
     */
    @Test
    public void testFind(){
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        Optional<String> first = Arrays.stream(strings)
                .findFirst();
        System.out.println(first.get());

        System.out.println("##################");

        Optional<String> any = Arrays.stream(strings).findAny();
        System.out.println(any.get());

        System.out.println("##################");
    }

    /**
     * 流的匹配
     * Stream 接口提供了三个方法，
     * 分别是 anyMatch（任何一个元素匹配，返回 true）、
     * allMatch（所有元素匹配，返回 true）、
     * noneMatch（没有一个元素匹配，返回 true）。
     */
    @Test
    public void testMatch(){
        boolean b = Stream.of(1, 2, 3, 4, 5, 10)
                .anyMatch(x -> x > 5);
        System.out.println(b);

        System.out.println("##################");

        boolean b2 = Stream.of(1, 2, 3, 4, 5, 10)
                .allMatch(x -> x > 5);
        System.out.println(b2);

        System.out.println("##################");

        boolean b3 = Stream.of(1, 2, 3, 4, 5, 10)
                .noneMatch(x -> x > 5);
        System.out.println(b3);
    }
}
