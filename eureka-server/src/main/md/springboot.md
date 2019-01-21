---
layout: post
title:  "springboot"
date:   2019-01-13 13:35:21
author: zhangtejun
categories: springboot
---
# springboot

## 启动引导类

`@SpringBootApplication`是一个复合注解,用于开启组件扫描和自动配置。
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration # 该注解和@Configuration作用相同，表示其标注的类是IoC容器的配置类。
@EnableAutoConfiguration # 该注解用于将所有符合自动配置的Bean加载到到前springboot创建并使用的IoC容器中。
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
) # 该注解用于自动扫描和加载符合条件的组件或者Bean,并将Bean加载到IoC容器中。
public @interface SpringBootApplication {
```

>The new operator creates an instance of a user-defined object type or of one of the built-in object types that has a constructor function.

