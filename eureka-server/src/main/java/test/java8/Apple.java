package test.java8;

import lombok.Data;


@Data
public class Apple implements Comparable<Apple>{
    private String color;
    private int weigth;

    Apple(String color, int weigth){
        this.color= color;
        this.weigth = weigth;
    }
    Apple(){

    }
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeigth() > 150;
    }

    @Override
    public int compareTo(Apple o) {
        return this.getColor().compareTo(o.getColor());
    }
}
