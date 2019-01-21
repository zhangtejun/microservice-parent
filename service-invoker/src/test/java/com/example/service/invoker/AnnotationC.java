package com.example.service.invoker;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnotationC {
    AnnotationB [] value();
}
