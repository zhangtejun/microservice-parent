---
layout: post
title:  "Singleton"
date:   2019-05-07 19:53:02
author: zhangtejun
categories: Mybatis
---
##### Singleton

```java
public class Singleton {
    // 1.饿汉式
    // 在类加载时就创建对象
   private static final Singleton instance = new Singleton();

   // 私有构造函数
    private Singleton(){
    }
    // 返回唯一实例
    public static Singleton getInstance() {
        return instance;
    }

    // 2.懒汉式
    private static  Singleton instance1 = null;
    public static synchronized Singleton getInstance1() {
        // 在需要时再创建对象
        if(instance1 == null){
            instance1 = new Singleton();
        }
        return instance1;
    }

    // 2.1懒汉式 双重锁检验 --反模式
    private static  volatile Singleton instance1_1 = null;// 由于指令重排序等，需要加volatite关键字
    public static  Singleton getInstance1_1() {
        // 在需要时再创建对象
        if(instance1_1 == null){
            synchronized(Singleton.class){
                if (instance1_1 == null){
                    instance1_1 = new Singleton();
                }
            }
        }
        return instance1_1;
    }

    // 3. 私有静态内部类
    private   static class SingletonHolder{
        public static Singleton instance2 = new Singleton();
    }

    public static Singleton getNewInstance() {
        return SingletonHolder.instance2;
    }
}
```