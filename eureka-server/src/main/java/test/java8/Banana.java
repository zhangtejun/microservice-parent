package test.java8;

import lombok.Data;

import java.util.Comparator;


@Data
public class Banana {
    private String color;
    private int weigth;

    Banana(String color, int weigth){
        this.color= color;
        this.weigth = weigth;
    }
    public static boolean isGreenApple(Banana apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Banana apple) {
        return apple.getWeigth() > 150;
    }

}
