package com.example.service.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CacheService {
    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(commandKey = "AAA")
    @CacheResult
    public String getFromCache(@CacheKey String str){
        String json = restTemplate.getForObject("http://service-provider/testHystrixCache/{string}",String.class,str);
        log.info("@@@@@@@@@@@@@@@@@@------------>" + json);
        return json;
    }
    @CacheRemove(commandKey = "AAA")
    @HystrixCommand
    public String update(@CacheKey String str){
        return "okk";
    }
}
