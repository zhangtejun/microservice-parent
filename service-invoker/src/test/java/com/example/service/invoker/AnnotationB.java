package com.example.service.invoker;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AnnotationC.class)
@Inherited
public @interface AnnotationB {
    boolean hasTrue() default false;
    String value() default "";
}
