package com.example.service.invoker.service;

import com.example.service.invoker.config.HystrixThreadLocal;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Service
public class ThreadContextService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand
    public String getUser(Integer id) {
        log.info("ThreadContextService ,Current thread : " + Thread.currentThread().getName() + " : id--->  " + Thread.currentThread().getId());
        log.info("ThreadContextService ,ThreadContext object  : " + HystrixThreadLocal.threadLocal.get());
        log.info("ThreadContextService "+RequestContextHolder.currentRequestAttributes().getAttribute("userId",RequestAttributes.SCOPE_REQUEST));
        System.out.println(RequestContextHolder.currentRequestAttributes().getAttribute("context", RequestAttributes.SCOPE_REQUEST));
        String json = restTemplate.getForObject("http://service-provider/testHystrixCache/{string}", String.class, id);
        log.info("@@@@@@@@@@@@@@@@@@------------>ThreadContextService :" + json);
        return json;
    }
}
