package test.java8;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class JavaTest {

    static List<Apple> filterApples(List<Apple> inventory,
                                    Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 引入泛型T
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green",20));
        inventory.add(new Apple("red",30));
        inventory.add(new Apple("red",300));
        inventory.add(new Apple("green",200));
        // 过滤结果并输出
        filterApples(inventory, Apple::isGreenApple).forEach(System.out::println);
        filterApples(inventory,apple -> apple.getColor().equals("green")).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(new Integer(20),new Integer(13),new Integer(100));

        filter(numbers,(Integer i)-> i % 2 == 0).forEach(System.out::println);

/*        Collections.sort(inventory);
        inventory.forEach(System.out::println);*/

        inventory.sort(Apple::compareTo);
        inventory.forEach(System.out::println);

        // 排序
        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        inventory.sort((a,b)->a.getColor().compareTo(b.getColor()));
        inventory.sort(Comparator.comparing(Apple::getColor).thenComparing(Apple::getWeigth));

        numbers.sort((a,b)->a.compareTo(b));
        numbers.sort(Comparator.naturalOrder());


        List<Banana> list = Arrays.asList(new Banana("G",12),new Banana("R",123),new Banana("G",20));
        list.sort(new ComparatorTest().reversed());
        list.forEach(System.out::println);


        Comparator<Banana> comparator = Comparator.comparing(Banana::getColor,Comparator.nullsFirst(Comparator.naturalOrder()));
        list.sort(comparator);

        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        Supplier<Apple> c1 = Apple::new;


        BiFunction<String,Integer, Apple> c2 = Apple::new;

        Predicate<Apple> redAndHeavyAppleOrGreen = apple -> apple.getColor().equals("red");
        redAndHeavyAppleOrGreen.negate().and(apple -> apple.getWeigth()<100).or(
                apple -> apple.getWeigth()>10
        );
        

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);//数学上一般会写作g(f(x))
        System.out.println(result);
    }

}
