package test.java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DishTest {
  public static   List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) ,
            new Dish("salmon", false, 450, Dish.Type.FISH)

  );

    public static void main(String[] args) throws IOException {
        System.out.println(menu.stream().count());

        menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));


        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);


        Stream<Integer> integerStream = menu.stream().map(Dish::getCalories);
       /* List<Dish> dishes = menu.parallelStream().filter(Dish::isVegetarian).collect(Collectors.toList());
       // dishes.forEach(System.err::println);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i,j})).collect(Collectors.toList());


        dishes.parallelStream().filter(Dish::isVegetarian).findFirst().ifPresent(d -> System.out.println(d.getName()));
         numbers1.stream().reduce((a, b) -> (a + b)).ifPresent(d -> System.out.println(d));


        Stream.of("java1.8","123asd","abc").map(String::toLowerCase).forEach(System.out::println);

        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\aa"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        }

        Stream.iterate(0,n -> n+1).limit(3).forEach(System.err::println);
        Stream.generate(Math::random).limit(5).forEach(System.out::println);*/
    }
}
