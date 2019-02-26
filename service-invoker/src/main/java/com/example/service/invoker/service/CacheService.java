package com.example.service.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CacheService {
    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "defaultFallback",fallbackMethod = "defaultFallback")
    @CacheResult
    public String getFromCache(@CacheKey String str) throws Exception {
        String json = restTemplate.getForObject("http://service-provider/testHystrixCache/{string}",String.class,str);
        log.info("@@@@@@@@@@@@@@@@@@------------>" + json);

        Method[] methods = CacheService.class.getMethods();
        List<Method> getFromCache = Arrays.stream(methods).filter(method -> method.getName().equals("getFromCache")).collect(Collectors.toList());
        Arrays.stream(getFromCache.get(0).getDeclaredAnnotations()).forEach(System.out::println);

        throw new Exception("242");
        //return json;
    }
    @CacheRemove(commandKey = "AAA")
    @HystrixCommand
    public String update(@CacheKey String str){

        return "okk";
    }

    public String defaultFallback(String i){


        return "defaultFallback";
    }


    public String updateSome(@CacheKey String str){
        //
        //logService.insert("用户1 更新了  XXX   2019-01-09");

        return "okk";
    }
}
