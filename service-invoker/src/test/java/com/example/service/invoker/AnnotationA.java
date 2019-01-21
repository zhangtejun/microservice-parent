package com.example.service.invoker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationA {
    // 1，默认是 public abstract
    // 2, 同时可选择使用default提供默认值,public abstract String value() default "";
    String value();
    // 3, 元素数据类型,可以是所有基本类型（int,float,boolean,byte,double,char,long,short），String，Class，enum，Annotation，及其类型的数组
    boolean hasNext() default false;
    enum Status {RED,BLUE}
    Class<?> testCase() default String.class;
    long[] lValues();
    AnnotationB annotationB() default @AnnotationB(value="AnnotationB",hasTrue=true);
}
