package com.example.service.invoker.interceptor;

import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CacheInterceptor implements HandlerInterceptor {
    //初始化Hystrix请求上下文
    HystrixRequestContext context ;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        context= HystrixRequestContext.initializeContext();
        log.info("--------》 HystrixRequestContext.initializeContext end ");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        context.shutdown();
        log.info("--------》 HystrixRequestContext.initializeContext shutdown ");
    }
}
