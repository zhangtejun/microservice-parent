package com.example.service.invoker;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@AnnotationA(value = "aa",lValues = {1,2,3})
public class AnnotationDemo extends AnnotationSub {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = AnnotationDemo.class;
        Annotation[] annotations =clazz.getAnnotations();//返回此元素上存在的所有注解，包括从父类继承的
        System.out.println("an:"+ Arrays.toString(annotations));

        Arrays.stream(annotations).forEach(System.out::println);

        AnnotationA annotationA = clazz.getAnnotation(AnnotationA.class);//该元素如果存在指定类型的注解，则返回这些注解，否则返回 null。
        System.out.println(annotationA);

        Annotation[] annotations1 = clazz.getDeclaredAnnotations();//返回直接存在于此元素上的所有注解不包括继承
        Arrays.stream(annotations1).forEach(System.out::println);

        // Java 8中新增加的元注解@Repeatable，它表示在同一个位置重复相同的注解
        // Java8中 ElementType 新增两个枚举成员，TYPE_PARAMETER 和 TYPE_USE,新增的TYPE_PARAMETER可以用于标注类型参数，
        // 而TYPE_USE则可以用于标注任意类型(不包括class),TYPE_PARAMETER用来支持在Java的程序中做强类型检查.配合第三方插件工具,可以在编译的时候检测出runtimeerror
    }

}
