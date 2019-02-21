package com.example.service.invoker.controller;

import com.example.service.invoker.command.CacheCommand;
import com.example.service.invoker.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CacheController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CacheService cacheService;

    @GetMapping(value = "getFromCache/{string}")
    public String getFromCache(@PathVariable("string") String string){
        CacheCommand cacheCommand = new CacheCommand(restTemplate,string);
        cacheCommand.execute();
        log.info("from cache : "+ cacheCommand.isResponseFromCache());
        CacheCommand cacheCommand1 = new CacheCommand(restTemplate,string);
        cacheCommand1.execute();
        log.info("from cache : "+ cacheCommand1.isResponseFromCache());
        return "ok";
    }

    @GetMapping(value = "getFromCacheByAnnotation/{string}")
    public String getFromCacheByAnnotation(@PathVariable("string") String string){
        cacheService.getFromCache(string);
        cacheService.getFromCache(string);
        cacheService.update(string);
        cacheService.getFromCache(string);
        cacheService.getFromCache(string);
        return "ok";
    }
}
